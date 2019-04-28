package com.hemanshu;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import com.hemanshu.ItemService;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class LoginController {
	@Autowired
	UserRepo ur;
	String chatuser="";
	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;
	@Autowired
	ItemService itemservice;
	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ItemRepository itemrepository;

	@Autowired
	LoginNumberRepository loginNumberRepository;

	@Autowired
	RegisterNumberRepository registerNumberRepository;
	
	public List<Item> getProducts(String key){
		List<Item> itemList = new ArrayList<Item>();
		List<Item> resultSet = new ArrayList<Item>();

		itemList = itemservice.showAllitems();
		for(Item item: itemList){
			System.out.println(item.item_name + " "+ key);
			if (item.item_name.equals(key) ){
				resultSet.add(item);
			}
			else if (item.description.indexOf(key)!=-1){
				resultSet.add(item);
			}

		}
		return resultSet;
	}
    @RequestMapping("/message")
    public String message(){
    	return "Message";
    	
    }
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
       System.out.println("cchatt"+message.getUsername());
       
        return new Greeting( HtmlUtils.htmlEscape(message.getUsername()+": "+message.getName()) );
    }
	@RequestMapping("/admin")
	public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "adminpage";
	}

	@RequestMapping("/welcome")
	public String homepage()
	{
		return "index";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
		
	}
	@RequestMapping("/signup")
	public String signup()
	{
		RegisterNumbers r = new RegisterNumbers();
		r.setDate(getDateInString());
		r.setUser_id("signupID");
		registerNumberRepository.save(r);

		return "Signup";
	}

	@RequestMapping("/contact")
	public String contact()
	{
		return "contact";
	}

	@RequestMapping("/saveuser")
	public String saveuser(@ModelAttribute("user") User user,BindingResult bindingResult,HttpServletRequest request)
	{
		user.setRole("user");
		ur.save(user);
		return "index";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute("user") User user,BindingResult bindingResult,HttpServletRequest request)
	{
		HttpSession session = request.getSession();

	   System.out.println(user.getEmailID()+"    ####"+ user.getPassword());
		if(ur.findByEmailIDAndPassword(user.getEmailID(),user.getPassword())!=null)
		{
			session.setAttribute("username", user.getEmailID());
			chatuser=user.getEmailID();
//			System.out.print("Role in class:"+user.getRole());
			User d=ur.findByEmailIDAndPassword(user.getEmailID(),user.getPassword() );

			loginNumbers n = new loginNumbers();
			n.setDate(getDateInString());
			n.setUser_id(d.getEmailID());
			loginNumberRepository.save(n);
			
			if(d.getRole().equalsIgnoreCase("admin"))
			{
				session.setAttribute("role", "admin");
				request.setAttribute("mode", "MODE_HOME");
				return "adminpage";
			}
			else {
				session.setAttribute("role", "user");
				session.setAttribute("user", user);
				request.setAttribute("item", itemservice.showAllitems());
				request.setAttribute("mode", "All_Products");
				return "allproducts";}
		}
		return "index";
	}
	
	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "adminpage";
	}

	@RequestMapping("/search")
	public String searchProducts(@RequestParam String key, HttpServletRequest request) {
		System.out.println("Yo asshole, you typed "+ key);
		String[] searchString = key.split(",");
		List<Item> result = getProducts(searchString[1]);
		if (result.isEmpty())
		{
			request.setAttribute("mode", "NO_PRODUCT");
			return "allproducts";
		}
		else {
			request.setAttribute("item", result);
			request.setAttribute("mode", "SEARCH");
			return "allproducts";
		}
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		userService.deleteMyUser(id);
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "adminpage";
	}
	public Item findByIDnum(int id){
		for(Item item: itemservice.showAllitems()){
			if (item.id == id) {
				return item;
			};
		}
		return null;
	}



	@RequestMapping("/buy-item")
	public String renderBuyPage(@RequestParam int id, HttpServletRequest request) {
		Item a= findByIDnum(id);
		request.setAttribute("item", a);
		request.setAttribute("mode", "ALL_USERS");
		return "buypage";
	}

	@GetMapping("/all-items")
	public String showAllItems(HttpServletRequest request) {
		request.setAttribute("item", itemservice.showAllitems());
		request.setAttribute("mode", "All_items");
		return "adminpage";
	} 
	
	@RequestMapping("/delete-item")
	public String deleteItem(@RequestParam int ID, HttpServletRequest request) {
		itemservice.deleteMyitem(ID);
		request.setAttribute("item", itemservice.showAllitems());
		request.setAttribute("mode", "All_items");
		return "adminpage";
	}

	@RequestMapping("/allproducts")
	public String allproducts(HttpServletRequest request) {
		request.setAttribute("item", itemservice.showAllitems());
		request.setAttribute("mode", "All_Products");
		return "allproducts";
	}

	@GetMapping("/allproductslist")
	public @ResponseBody
	Iterable<Item> getAllProducts()
     {
		// This returns a JSON or XML with the items

		return itemservice.showAllitems();
	}
	@RequestMapping("/email")
	public String email(HttpServletRequest request) {
		
		request.setAttribute("mode", "EMAIL");
		return "adminpage";
	}


	@RequestMapping(value="/sendemail", method=RequestMethod.POST)
	public String sendemail(@RequestParam("text") String text,@RequestParam("subject") String subject,HttpServletRequest request) {
		try {
			
			emailService.sendEmail(userService.showAllUsers(),text,subject );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("mode", "SENDEMAIL");
		return "adminpage";
	}

	@RequestMapping(value="/emailFriend", method=RequestMethod.POST)
	public String emailFriend(@ModelAttribute("user") User user, @RequestParam String friend, @RequestParam String itemDetails,HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Object email = session.getAttribute("username");
			System.out.println("This variable has value " + (String) email  + "\n **************");
			emailService.emailFriend(friend, itemDetails,"Check out this product at Sell It Down", (String) email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "buypage";
	}

	@GetMapping("/seeReviews")
	public String seeReviews(@RequestParam String id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<User> listOfUsers = userService.showAllUsers();
		int ID = 0;
		for(User oneUser : listOfUsers){
			if(oneUser.getName().equals(id)){
				ID = oneUser.getID();
				System.out.println("It entered here**********************");
				session.setAttribute("theSellerID", ID);
				break;
			}
		}


		try {
			Optional<Review> review = reviewRepository.findById(ID);
			Review userReview = review.get();
			String allReviews = userReview.getReviews();
			String[] s = allReviews.split(",");
			request.setAttribute("allReviews", allReviews);
			return "review";
		} catch (Exception e) {
			Review newReview = new Review();
			newReview.setID(ID);
			String allReviews = "";
			String[] s = allReviews.split(",");
			newReview.setReviews(allReviews);
			System.out.println(newReview.getReviews());
			for (String review : s) {
				System.out.println("*****" + review);

			}
			request.setAttribute("allReviews", s);
			try {
				return "review";
			} catch (Exception p) {
				System.out.println(p);
			}
			return "nothing";


		}
	}


	@PostMapping("/submitReview")
	public String submitReview(@RequestParam String review,  HttpServletRequest request){
		HttpSession session = request.getSession();
		Object identity = session.getAttribute("theSellerID");
		int id = (int) identity;
		String allReviews=",";
		System.out.println("The Seller id "+id+"********************\n*******************");
		List<User> listOfUsers = userService.showAllUsers();
		for(User oneUser : listOfUsers){
			if( oneUser.getID() == id){
				Optional<Review> reviews = reviewRepository.findById(id);

				try {
					reviews.get();
				}
				catch(Exception e) {
					Review newReview = new Review();
					allReviews = review;
					newReview.setID(id);
					newReview.setReviews(allReviews);
					request.setAttribute("allReviews", allReviews);
					reviewRepository.save(newReview);
					return "review";
				}

				Review reviewToAdd = reviews.get();
				allReviews = reviewToAdd.getReviews();
				System.out.println("Before adding" + allReviews);
				allReviews = allReviews + ", " + review;
				System.out.println("After adding" + allReviews);
				reviewToAdd.setReviews(allReviews);
				String[] s = allReviews.split(",");
				request.setAttribute("allReviews", s);
				reviewRepository.save(reviewToAdd);
				return "review";
			}
		}

		return "review";

	}

	public String getDateInString(){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
