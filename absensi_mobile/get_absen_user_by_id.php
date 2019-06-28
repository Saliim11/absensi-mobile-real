<?php

include "connect.php";

$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

$id_absen = $_GET["id_absen"];

$reference = "SELECT * FROM `absen_user` WHERE `id_absen` = '$id_absen' ORDER BY `jam_masuk` DESC";
$resultReference = mysqli_query($con, $reference);

$response = array();

foreach ($resultReference as $key => $value) {
    array_push($response, $value);
}

print(json_encode($response));
?>