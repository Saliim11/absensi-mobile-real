<?php

include "connect.php";

$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

$reference = "SELECT * FROM `lokasi_event`";
$resultReference = mysqli_query($con, $reference);

$response = array();

foreach ($resultReference as $key => $value) {
    array_push($response, $value);
}

print(json_encode($response));
?>