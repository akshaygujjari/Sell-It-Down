package com.hemanshu;

import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "admin/statistics")
public class statisticsController {

    @Autowired
    UserRepo ur;

    @Autowired
    LoginNumberRepository loginNumberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RegisterNumberRepository registerNumbersRepository;

    @RequestMapping(value = "")
    public String statHome(Model model) {

        Iterable<loginNumbers> l = loginNumberRepository.findAll();
        model.addAttribute("totalLogin", loginNumberRepository.count());
        model.addAttribute("itemCount", itemRepository.count());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
        Date date = new Date();

        model.addAttribute("date1",formatter.format(date));
        model.addAttribute("loginNum1", loginCount(date));
        model.addAttribute("registerNum1", registerCount(date));

        final Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, -1);
        model.addAttribute("date2",formatter.format(cal.getTime()));
        model.addAttribute("loginNum2", loginCount(cal.getTime()));
        model.addAttribute("registerNum2", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date3",formatter.format(cal.getTime()));
        model.addAttribute("loginNum3", loginCount(cal.getTime()));
        model.addAttribute("registerNum3", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date4",formatter.format(cal.getTime()));
        model.addAttribute("loginNum4", loginCount(cal.getTime()));
        model.addAttribute("registerNum4", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date5",formatter.format(cal.getTime()));
        model.addAttribute("loginNum5", loginCount(cal.getTime()));
        model.addAttribute("registerNum5", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date6",formatter.format(cal.getTime()));
        model.addAttribute("loginNum6", loginCount(cal.getTime()));
        model.addAttribute("registerNum6", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date7",formatter.format(cal.getTime()));
        model.addAttribute("loginNum7", loginCount(cal.getTime()));
        model.addAttribute("registerNum7", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date8",formatter.format(cal.getTime()));
        model.addAttribute("loginNum8", loginCount(cal.getTime()));
        model.addAttribute("registerNum8", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date9",formatter.format(cal.getTime()));
        model.addAttribute("loginNum9", loginCount(cal.getTime()));
        model.addAttribute("registerNum9", registerCount(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        model.addAttribute("date0",formatter.format(cal.getTime()));
        model.addAttribute("loginNum0", loginCount(cal.getTime()));
        model.addAttribute("registerNum0", registerCount(cal.getTime()));

        return "statHome";
    }

    @RequestMapping(value = "/user")
    public String statUser() {
        return "statUser";
    }

    @RequestMapping(value = "/item")
    public String statItem(Model model) {

        ArrayList<Item> items = (ArrayList)itemRepository.findAll();
        int soldCount=0, unsoldCount=0, overHundred=0,overFifty=0,overTen=0,notOver=0;
        int i = 0;
        while(i<items.size()){
            float sp = Float.valueOf(items.get(i).getSelling_price());
            boolean status = items.get(i).getstatus();

            if(status){
                soldCount++;
            }else{
                unsoldCount++;
            }

            if(sp>100){
                overHundred++;
            }else if(sp>50){
                overFifty++;
            }else if(sp>10){
                overTen++;
            }else{
                notOver++;
            }

            i++;
        }

        model.addAttribute("itemCount", itemRepository.count());
        model.addAttribute("soldCount",soldCount);
        model.addAttribute("unsoldCount",unsoldCount);
        model.addAttribute("OH",overHundred);
        model.addAttribute("OF",overFifty);
        model.addAttribute("OT",overTen);
        model.addAttribute("NT",notOver);
        System.out.println(soldCount+"!"+unsoldCount+"!"+overHundred+"!"+overFifty+"!"+overTen+"!"+notOver);
        return "statItem";
    }

    private int loginCount(Date date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));

        ArrayList<loginNumbers> l = loginNumberRepository.findByDate(formatter.format(date));
        HashMap<String, String> hmap = new HashMap<String, String>();
        int i = 0;
        while(i<l.size()){
            hmap.put(l.get(i).getUser_id(),"");
            i++;
        }

        return hmap.size();
    }

    private int registerCount(Date date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));

        ArrayList<RegisterNumbers> l = registerNumbersRepository.findByDate(formatter.format(date));
        HashMap<String, String> hmap = new HashMap<String, String>();

        int i = 0;
        while(i<l.size()){
            hmap.put(l.get(i).getUser_id(),"");
            i++;
        }

        return l.size();
    }



}
