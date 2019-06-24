<?php

include "connect.php";
$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

$response = array();

$lokasi = $_POST['lokasi'];
$latitude = $_POST['latitude'];
$longitude = $_POST['longitude'];
$radius = $_POST['radius'];

$sql = "INSERT INTO lokasi_event(id, lokasi, latitude, longitude, radius) VALUES(UUID(),'$lokasi','$latitude','$longitude','$radius')";

$result = mysqli_query($con, $sql);

if ($result) {
    $resp["status"] = "1";
    $resp["message"] = "Create successfully"; 
    $resp["nama"] = $lokasi;
    $resp["latitude"] = $latitude; 
    $resp["latitude"] = $longitude; 
    $resp["radius"] = $radius;
}else{
	$resp["status"] = "0";
    $resp["message"] = "Create not successfully"; 
}

$response=$resp;  
echo json_encode($response);  

mysqli_close($con);
?>