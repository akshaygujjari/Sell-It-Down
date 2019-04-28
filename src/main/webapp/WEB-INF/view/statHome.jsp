<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <meta charset="UTF-8">
  <title>Statistics-Overview</title>

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

  <h1>All time users login count: ${totalLogin}</h1>
  <h1>Amount of items: ${itemCount}</h1>

  <script type="text/javascript">
      window.onload = function() {

          var options = {
              title: {
                  text: "Numbers of active users"
              },
              data: [
                  {
                      // Change type to "doughnut", "line", "splineArea", etc.
                      type: "column",
                      dataPoints: [
                          { label: "${date0}",  y: ${loginNum0}  },
                          { label: "${date9}",  y: ${loginNum9}  },
                          { label: "${date8}",  y: ${loginNum8}  },
                          { label: "${date7}",  y: ${loginNum7}  },
                          { label: "${date6}",  y: ${loginNum6}  },
                          { label: "${date5}",  y: ${loginNum5}  },
                          { label: "${date4}",  y: ${loginNum4}  },
                          { label: "${date3}",  y: ${loginNum3}  },
                          { label: "${date2}",  y: ${loginNum2}  },
                          { label: "${date1}",  y: ${loginNum1}  }
                      ]
                  }
              ]
          };
          var options2 = {
              title: {
                  text: "Numbers of new users"
              },
              data: [
                  {
                      // Change type to "doughnut", "line", "splineArea", etc.
                      type: "column",
                      dataPoints: [
                          { label: "${date0}",  y: ${registerNum0}  },
                          { label: "${date9}",  y: ${registerNum9}  },
                          { label: "${date8}",  y: ${registerNum8}  },
                          { label: "${date7}",  y: ${registerNum7}  },
                          { label: "${date6}",  y: ${registerNum6}  },
                          { label: "${date5}",  y: ${registerNum5}  },
                          { label: "${date4}",  y: ${registerNum4}  },
                          { label: "${date3}",  y: ${registerNum3}  },
                          { label: "${date2}",  y: ${registerNum2}  },
                          { label: "${date1}",  y: ${registerNum1}  }
                      ]
                  }
              ]
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