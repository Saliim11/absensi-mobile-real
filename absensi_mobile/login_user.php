<?php 
    
    include "connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);
    
    $response = array();

        $vsusername = $_POST['vsusername'];
        $vspassword = md5($_POST['vspassword']);
        // $vslevel = $_POST['vslevel'];

        $sql = "SELECT * FROM users WHERE vsusername= '$vsusername' and vspassword ='$vspassword'";
   
        // die($sql);
        #Query the database to get the user details. 
        $leveldetails = mysqli_query($con, $sql); 

        #If no data was returned, check for any SQL errors 
        if (!$leveldetails) { 
	echo 'Could not run query: ' . mysqli_error($con); 
           		exit;
        } 
  
        #Get the first row of the results 
        $row = mysqli_fetch_row($leveldetails); 
        #Build the result array (Assign keys to the values) 
        $result_data = array( 
            'id' => $row[0],
            'nama' => $row[1],
            'vsusername' => $row[2],
            'vspassword' => $row[3],
            'vslevel'   => $row[4]); 
	
        #Output the JSON data 
      if (mysqli_num_rows($leveldetails) > 0) {
      $response['result'] = "1";
      $response['msg'] = "Berhasil login!!";
      $response['user'] = $result_data;
      
     // $response['vsusername'] = $vsusername ;

    }else{
      $response['result'] = "0";
      $response['msg'] = "Gagal login!!";

    }
      echo json_encode($response);
     
?>