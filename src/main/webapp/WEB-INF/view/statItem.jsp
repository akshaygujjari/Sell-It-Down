<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <meta charset="UTF-8">
  <title>Statistics-Users</title>

  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Statistics</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Select
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/admin/statistics">Overview</a>
            <a class="dropdown-item" href="/admin/statistics/item">Item Based</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/admin">Home</a>
          </div>
        </li>
      </ul>
    </div>
  </nav>

  <h1>Amount of items: ${itemCount}</h1>
  <h1>Amount of items that are over $100: ${OH}</h1>
  <h1>Amount of items that are over $50: ${OF}</h1>
  <h1>Amount of items that are over $10: ${OT}</h1>
  <h1>Amount of items that are under or equals to $10: ${NT}</h1>

  <script type="text/javascript">
      window.onload = function() {

          var options = {
              exportEnabled: true,
              animationEnabled: true,
              title:{
                  text: "Sold and Unsold Amount"
              },
              legend:{
                  horizontalAlign: "right",
                  verticalAlign: "center"
              },
              data: [{
                  type: "pie",
                  showInLegend: true,
                  toolTipContent: "<b>{name}</b>: ${y} (#percent%)",
                  indexLabel: "{name}",
                  legendText: "{name} (#percent%)",
                  indexLabelPlacement: "inside",
                  dataPoints: [
                      { y: "${soldCount}", name: "Sold" },
                      { y: "${unsoldCount}", name: "Unsold" }
                  ]
              }]
          };
          var options2 = {
              exportEnabled: true,
              animationEnabled: true,
              title:{
                  text: "Percentage of Different Prices"
              },
              legend:{
                  horizontalAlign: "right",
                  verticalAlign: "center"
              },
              data: [{
                  type: "pie",
                  showInLegend: true,
                  toolTipContent: "<b>{name}</b>: ${x} (#percent%)",
                  indexLabel: "{name}",
                  legendText: "{name} (#percent%)",
                  indexLabelPlacement: "inside",
                  dataPoints: [
                      { y: "${OH}", name: "Over Hundred" },
                      { y: "${OF}", name: "Over Fifty" },
                      { y: "${OT}", name: "Over Ten" },
                      { y: "${NT}", name: "Under or Equals to Ten" }
                  ]
              }]
          };
          $("#chartContainer1").CanvasJSChart(options);
          $("#chartContainer2").CanvasJSChart(options2);




      }
  </script>

</head>
<body>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<div id="chartContainer1" style="height: 370px; width: 100%; float: right"></div>
<div id="chartContainer2" style="height: 370px; width: 100%; float: right"></div>

<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>


</body>
</html>