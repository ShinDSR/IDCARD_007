/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WSB.project02.passing.text.image;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author fa506
 */
@Controller
public class controller {
    
    @RequestMapping("/sendData")
    @ResponseBody
    public String getData(@RequestParam("nama") String getNama,
                          @RequestParam("tanggal") 
                          @DateTimeFormat(pattern="yyyy-MM-dd")Date getTanggal,
                          @RequestParam("foto") MultipartFile getFoto)
            throws IOException{
        
        SimpleDateFormat newTanggal = new SimpleDateFormat("EE-dd-MMMM-yyyy");
        
        String setTanggal = newTanggal.format(getTanggal);
        //String hariIni = getHari(newTanggal); 

        String blob = Base64.encodeBase64String(getFoto.getBytes());
        
        return "<head><title>WSB</title></head>"+
               "<body><div class='container'>"+
               "<br><center>ID CARD</center><hr/>"+
               "<p>Nama          : "+getNama+"</p>"+
               "<p>Tanggal Lahir : "+setTanggal+"</p>"+
               "<p><img src='data:image/jpeg;Base64,"+blob+"' alt='No Image' width='170' height='250'/></p></div>"+
                "<style>"+
                ".container {position: absolute; left: 50%;  top: 50%; transform: translate(-50%, -50%); height: 400px; width: 600px; background: cyan;"+
                "overflow: hidden; border-radius: 20px; box-shadow: 0 0 20px 8px #d0d0d0; background-size: cover;  background-repeat: no-repeat;}"+
                "p {font-weight: 900; text-align: left; width: 100%; direction: rtl; padding: 0 10px;}"+
                "center {font-weight: 900; text-align: center;}"+
                "hr { border: 1px solid black;}"+
                "img {padding: 0 400px;}"+
                "</style>";
    }
    
    public String getHari(String tanggal){
        String[] result = tanggal.split("-");
        if (result[0].equals("Thu")){result[0]="Kamis";}
        return result[0];
    }
    
}
