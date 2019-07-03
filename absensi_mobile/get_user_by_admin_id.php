<?php

include "connect.php";

$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

$id_admin = $_GET['id_admin'];

/*
$reference = "SELECT * FROM `absen_user` JOIN `users` ON `absen_user`.`id_user` = `users`.`id` WHERE `users`.`id_admin` = '$id_admin' ORDER BY `jam_masuk` DESC";
$resultReference = mysqli_query($con, $reference);
*/
$sql1 = "SELECT * FROM `users` WHERE `id_admin` = '$id_admin'";
$resultSql = mysqli_query($con, $sql1);

$response = array();

foreach ($resultSql as $key => $value) {
    array_push($response, $value);
}

print(json_encode($response));
?>