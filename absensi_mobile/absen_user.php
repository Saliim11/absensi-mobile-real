<?php  

include "connect.php";
$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

date_default_timezone_set('Asia/Jakarta');
$date = date("Y-m-d H:i:s");

$response = array();

$nama = $_POST['nama'];
$lokasi = $_POST['lokasi'];
$status_absen = $_POST['status_absen'];
$gambar = $_POST['gambar'];


$ImagePath = "$gambar";
 
$ServerURL = "$ImagePath";

$sql = "INSERT INTO absen_user(id, nama, lokasi, status_absen, gambar, created) VALUES(UUID(),'$nama','$lokasi','$status_absen','$ServerURL','".date('Y-m-d H:i:s')."')";

$result = mysqli_query($con, $sql);

if ($result) {
    $resp["status"] = "1";
    $resp["message"] = "Create successfully"; 
    $resp["nama"] = $nama;
    $resp["lokasi"] = $lokasi;
    $resp["status_absen"] = $status_absen;
    $resp["gambar"] = $gambar;
    

    echo "Your Image Has Been Uploaded.";

}else{
	$resp["status"] = "0";
    $resp["message"] = "Create not successfully"; 
}

$response=$resp;  
echo json_encode($response);  

mysqli_close($con);

?>