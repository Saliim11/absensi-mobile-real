<?php

include "connect.php";
$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

$response = array();

$nama = $_POST['nama'];
$username = $_POST['vsusername'];
$password = md5($_POST['vspassword']);
$level = "normal user";
$id_admin = $_POST['id_admin'];

$sql = "INSERT INTO users(id, nama, vsusername, vspassword, vslevel, id_admin) VALUES(UUID(),'$nama','$username','$password','$level','$id_admin')";

$result = mysqli_query($con, $sql);

if ($result) {
    $resp["status"] = "1";
    $resp["message"] = "Create successfully"; 
    $resp["nama"] = $nama;
    $resp["vsusername"] = $username; 
    $resp["vslevel"] = $level; 
    $resp["id_admin"] = $id_admin;
}else{
	$resp["status"] = "0";
    $resp["message"] = "Create not successfully"; 
}

$response=$resp;  
echo json_encode($response);  

mysqli_close($con);
?>