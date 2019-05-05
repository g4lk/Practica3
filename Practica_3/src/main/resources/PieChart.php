<?php
	$con = mysqli_connect('localhost','root','','practica3');
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
	google.charts.load('current', {'packages':['table']});
	google.charts.load('current', {'packages':['gauge']});

	google.setOnLoadCallback(drawChart);
	google.setOnLoadCallback(drawChart2);
	google.setOnLoadCallback(drawChart3);


	//PieChart
	function drawChart() {

		var data = google.visualization.arrayToDataTable([
			['importe','id_producto'],
			<?php
					$query = "SELECT * FROM compra";
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

	//GaugeChart

	function drawChart2() {

		var data = google.visualization.arrayToDataTable([
			['valoracion','id_cliente'],
			<?php
					$query = "SELECT *,count(id_producto) FROM compra WHERE id_cliente = '1' GROUP BY id_producto";
					$exec = mysqli_query($con,$query);
					while($row = mysqli_fetch_array($exec)){
						echo "['".$row['valoracion']."',".$row['id_cliente']."],";
					}
				?>
		]);

		var options = {
			title: 'Number of valorations according to their customer',
			min: 0, max: 700,
			redFrom: 0, redTo: 300,
			yellowFrom: 300, yellowTo: 500,
			greenFrom: 500, greenTo: 700,
			minorTicks: 0,
		};

		var chart = new google.visualization.Gauge(document.getElementById("GaugeChart"));

		chart.draw(data,options);
	}
	// Table recommends
	function drawChart3() {

		var data = google.visualization.arrayToDataTable([
			['id_usuario','id_producto','valoracion'],
			<?php
				$query = "SELECT * FROM recomendacion";
				$exec = mysqli_query($con,$query);
				while($row = mysqli_fetch_array($exec)){
					echo "['".$row['id_usuario']."','".$row['id_producto']."',".$row['valoracion']."],";
				}
			?>
		]);

		var table = new google.visualization.Table(document.getElementById('TableChart'));

        	table.draw(data, {showRowNumber: true, width: '75%', height: '100%'});

	}


 </script>

</head>
<body>
	<div class="container-fluid">
		<div id="PieChart" style="width: 100%; height: 500px;"></div>
		<div id="GaugeChart" style="width: 100%; height: 500px;"></div>
		<div id="TableChart" style="height: 100px;"></div>
 	</div>
</body>
</html>