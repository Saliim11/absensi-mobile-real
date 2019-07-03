<?php

include "connect.php";

$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

$id_admin = $_GET['id_admin'];

$bulan = $_GET['jam_masuk'];
$nama = $_GET['id_user'];

$tahun = date('Y');
/*
$reference = "SELECT * FROM `absen_user` JOIN `users` ON `absen_user`.`id_user` = `users`.`id` WHERE `users`.`id_admin` = '$id_admin' ORDER BY `jam_masuk` DESC";
$resultReference = mysqli_query($con, $reference);
*/
$sql1 = "SELECT * FROM `users` WHERE `id_admin` = '$id_admin'";
$resultSql = mysqli_query($con, $sql1);
while($row = mysqli_fetch_array($resultSql)){
    $id[] = $row['id'];
};
$ids = join("','", $id);

if ($nama == null && $bulan == null){
    $sql2 = "SELECT * FROM `absen_user` WHERE `id_user` IN ('$ids') ORDER BY `jam_masuk` DESC";
    $resultSql = mysqli_query($con, $sql2);

} else if (!$bulan) {
    $sql3 = "SELECT * FROM `absen_user` WHERE `id_user` = '$nama' ORDER BY `jam_masuk` DESC";
    $resultSql = mysqli_query($con, $sql3);
    
} else if (!$nama) {
    $sql4 = "SELECT * FROM `absen_user` WHERE `id_user` IN ('$ids') AND `jam_masuk` LIKE '$tahun-$bulan%' ORDER BY `jam_masuk` DESC";
    $resultSql = mysqli_query($con, $sql4);
} else {
    $sql5 = "SELECT * FROM `absen_user` WHERE `id_user` = '$nama' AND `jam_masuk` LIKE '$tahun-$bulan%' ORDER BY `jam_masuk` DESC";
    $resultSql = mysqli_query($con, $sql5);
}

$response = array();

foreach ($resultSql as $key => $value) {
    array_push($response, $value);
}

print(json_encode($response));
?>