<?php  

include "connect.php";
$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

date_default_timezone_set('Asia/Jakarta');

$response = array();

$id_absen = $_GET['id_absen'];
$jam_pulang = date('Y-m-d H:i:s');

$sql = "UPDATE `absen_user` SET `jam_pulang` = '$jam_pulang' WHERE id_absen = '$id_absen' ";

$result = mysqli_query($con, $sql);

if ($result) {
    $resp["status"] = "1";
    $resp["message"] = "Update success"; 

}else{
	$resp["status"] = "0";
    $resp["message"] = "Update not success"; 
}

$response=$resp;  
echo json_encode($response);  

mysqli_close($con);

?>