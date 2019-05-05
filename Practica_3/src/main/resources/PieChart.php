<?php
	$con = mysqli_connect('localhost','root','1234','practica3');
?>

<!DOCTYPE HTML>
<html>
<head>
 <meta charset="utf-8">
 <title>Pie Chart</title>
 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.setOnLoadCallback(drawChart);
	 
	function drawChart() {
		  
		var data = google.visualization.arrayToDataTable([
			['importe','id_producto'],
			<?php 
				$query = "SELECT * FROM `TABLE compra` ";
				$exec = mysqli_query($con,$query);
				while($row = mysqli_fetch_array($exec)){
					echo "['".$row['importe']."',".$row['id_producto']."],";
				}
			?> 
		 ]);

		var options = {
			title: 'Number of sales according to their product',
			pieHole: 0.5,
			pieSliceTextStyle: {
				color: 'black',
			},
			legend: 'top',
			is3D: false
		};
		
		var chart = new google.visualization.PieChart(document.getElementById("PieChart"));
		
		chart.draw(data,options); 
	}
	
 </script>

</head>
<body>
 <div class="container-fluid">
 <div id="PieChart" style="width: 100%; height: 500px;"></div>
 </div>
</body>
</html>
