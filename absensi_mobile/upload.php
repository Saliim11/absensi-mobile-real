<?php
	include "connect.php";

	$con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

	$action = htmlspecialchars($_POST['action']);

	$response = array("success" => FALSE);

	if($action == "multipart") {
	    if ($_FILES["photo"]["error"] > 0) {
	    	$response["success"] = FALSE;
			$response["message"] = "Upload Failed";
	    } else {
			$name_file=htmlspecialchars($_FILES['photo']['name']);
			$path = "gambar/" . $name_file;



			
	        if (@getimagesize($_FILES["photo"]["tmp_name"]) !== false) {

				move_uploaded_file($_FILES["photo"]["tmp_name"], $path);

				$response["success"] = TRUE;
				$response["message"] = "Upload Successfull";
				$response["path"] = $path;
				
			}else{
				$response["success"] = FALSE;
				$response["message"] = "Upload Failed";
			}

			echo json_encode($response);
	    }
	}else if($action == "base64") {
		$photo = htmlspecialchars($_POST['photo']);

		$photo = str_replace('data:image/png;base64,', '', $photo);
		$photo = str_replace(' ', '+', $photo);

		$data = base64_decode($photo);
		$file = uniqid() . '.png';

		file_put_contents($file, $data);

		$response["success"] = TRUE;
		$response["message"] = "Upload Successfull";
		
		echo json_encode($response);
	}

?>