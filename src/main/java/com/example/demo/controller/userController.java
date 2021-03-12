package com.example.demo.controller;

import com.example.demo.config.BaiDuOCR;
import com.example.demo.dao.userRepository;
import com.example.demo.dao.userfollowerRepository;
import com.example.demo.entity.user;
import com.example.demo.service.FLKServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class userController {
    //图片的物理存储路径
    private static final String picPath = "F:/大三上课程/高级网络编程/pic/";
    //设置的tomcat静态虚拟路径
    private static final String vPicPath = "http://localhost:8080/pic/";
    @Autowired
    private FLKServiceImpl FLKServiceImpl;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private userfollowerRepository userfollowerRepository;
    @RequestMapping(value="/registered",method= RequestMethod.POST)
    @ResponseBody
    public Object save(HttpServletRequest request,HttpSession session) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setCharacterEncoding("utf-8");
        Map<String,Object> result=new HashMap<>();
        System.out.println(request.getParameter("name"));
        if(FLKServiceImpl.ensurefindBylogname(request.getParameter("name"))){

        }else{
            result.put("status",0);
            return result;
        }
        user u=new user();
        u.setLogname(request.getParameter("name"));
        u.setPassword(request.getParameter("password"));
        u.setEmail(request.getParameter("email"));
        u.setPhone(request.getParameter("phone"));
        FLKServiceImpl.save(u);
        /*u.setMessage(request.getParameter("message"));
        /*String picName=request.getParameter("name");*/
        //System.out.println(pic.getBytes().toString());
        //String fileName = request.getParameter("name")+".png";
        /*if(!pPicture.isEmpty()){
            byte [] bytes = pPicture.getBytes();
            BufferedOutputStream bufferedOutputStream = new
                    BufferedOutputStream(new FileOutputStream(new File("F:\\pic\\"+fileName)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
        }
        System.out.println("用户:"+request.getParameter("name"));*/
        result.put("status",1);
        return result;
    }
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request,HttpSession session) {
        /*response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setCharacterEncoding("utf-8");*/
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");
        Map<String,Object> result=new HashMap<>();
        System.out.println("name:"+request.getParameter("name"));
        System.out.println("password:"+request.getParameter("password"));
        int ensure=FLKServiceImpl.findBylogname(request.getParameter("name"),request.getParameter("password"));
        System.out.println("ensure:"+ensure);
        if(ensure==1){
            session.setAttribute("logname", request.getParameter("name"));
            System.out.println("sessionId:"+session.getId());
            result.put("status",1);
            System.out.println("status:"+result.get("status"));
        }else if(ensure==2){
            result.put("status",2);
        }else{
            result.put("status",3);
        }
        result.put("test",404);
        return result;
    }
    @RequestMapping(value="/exit",method= RequestMethod.POST)
    @ResponseBody
    public Object Index(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");

        Map<String,Object> result=new HashMap<>();
        System.out.println("delId:"+session.getId());
        if(session.getAttribute("logname")!=null){
            session.removeAttribute("logname");
            result.put("status","1");
            return result;
        }else{
            result.put("status","0");
            return result;
        }
    }
    @RequestMapping(value="/ensure",method= RequestMethod.POST)
    @ResponseBody
    public Object ensure(HttpServletRequest request,HttpSession session) {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");

        Map<String,Object> result=new HashMap<>();
        System.out.println("checkSessionId:"+session.getId());
        if(session.getAttribute("logname")!=null){
            result.put("status","1");
            return result;
        }else{
            result.put("status","0");
            return result;
        }
    }
    @RequestMapping(value="/infoReturn",method= RequestMethod.POST)
    @ResponseBody
    public Object infoReturn(HttpServletRequest request,HttpSession session) {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");

        Map<String,Object> result=new HashMap<>();
        System.out.println("checkReturnSessionId:"+session.getId());

        if(session.getAttribute("logname")==null){
            result.put("status",0);
            return result;
        }
        String logname= (String) session.getAttribute("logname");
        user u=userRepository.findBylogname(logname);
        String pic=u.getPic();
        String email=u.getEmail();
        String phone=u.getPhone();
        String message=u.getMessage();
        String Idcardforward=u.getIdcardforward();
        String Idcardbackward=u.getIdcardbackward();
        System.out.println("pic:"+pic);
        System.out.println("email:"+email);
        System.out.println("phone:"+phone);
        System.out.println("message:"+message);
        result.put("logname",logname);
        result.put("pic",pic);
        result.put("email",email);
        result.put("phone",phone);
        result.put("message",message);
        result.put("Idcardforward",Idcardforward);
        result.put("Idcardbackward",Idcardbackward);
        result.put("status",1);
        return result;
    }

    @RequestMapping(value="/onloadinfo",method= RequestMethod.POST)
    @ResponseBody
    public Object onloadinfo(HttpServletRequest request,HttpSession session,@RequestParam("pic") MultipartFile pPicture) throws IOException {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");

        Map<String,Object> result=new HashMap<>();
        System.out.println("checkOnloadSessionId:"+session.getId());
        System.out.println("email:"+request.getParameter("email"));
        System.out.println("phone:"+request.getParameter("phone"));
        System.out.println("message:"+request.getParameter("message"));
        String logname= (String) session.getAttribute("logname");
        user u=userRepository.findBylogname(logname);
        u.setEmail(request.getParameter("email"));
        u.setPhone(request.getParameter("phone"));
        u.setMessage(request.getParameter("message"));
        /*u.setMessage(request.getParameter("message"));
        /*String picName=request.getParameter("name");*/
        System.out.println("pic:"+pPicture.getBytes().toString());
        String fileName = logname+".png";
        if(!pPicture.isEmpty()){
            byte [] bytes = pPicture.getBytes();
            BufferedOutputStream bufferedOutputStream = new
                    BufferedOutputStream(new FileOutputStream(new File("F:\\java代码\\friendlooking\\src\\main\\resources\\userPic\\"+fileName)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
            u.setPic("../userPic/" +fileName);
        }
        userRepository.save(u);
        result.put("status","1");
        return result;
    }
    @RequestMapping(value="/onloadPassword",method= RequestMethod.POST)
    @ResponseBody
    public Object onloadPassword(HttpServletRequest request,HttpSession session) {
        /*response.addHeader("Access-Control-Allow-Origin", "*"); */  //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");

        Map<String,Object> result=new HashMap<>();
        System.out.println("now-password:"+request.getParameter("now-password"));
        System.out.println("new-password:"+request.getParameter("new-password"));
        System.out.println("ensure-newpassword:"+request.getParameter("ensure-newpassword"));
        if(!request.getParameter("new-password").equals(request.getParameter("ensure-newpassword"))){
            result.put("status","1");//前后新密码不一致
            return result;
        }
        String logname= (String) session.getAttribute("logname");
        user u=userRepository.findBylogname(logname);
        String password=u.getPassword();
        if(!request.getParameter("now-password").equals(password)){
            result.put("status","2");//新旧密码不一致
            return result;
        }
        u.setPassword(request.getParameter("new-password"));
        userRepository.save(u);
        result.put("status","3");
        session.removeAttribute("logname");
        return result;
    }

    @RequestMapping(value="/returnPages",method= RequestMethod.POST)
    @ResponseBody
    public Object returnPages(HttpServletRequest request,HttpSession session)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");

        Map<String,Object> result=new HashMap<>();
        Map<String,Object> page=new HashMap<>();
        String num=request.getParameter("page");
        System.out.println("page:"+num);
        if(num==null){
            num="1";
        }
        page=FLKServiceImpl.findAlluser(Integer.parseInt(num));
        int pages= (int) page.get("TotalPages");
        List<user> alluser= (List<user>) page.get("alluser");
        int i=1;
        for(user u:alluser){
            System.out.println(u.getLogname()+":"+u.getPic()+":"+u.getEmail()+":"+u.getMessage());
            result.put("Logname"+i,u.getLogname());
            result.put("Pic"+i,u.getPic());
            result.put("Email"+i,u.getEmail());
            result.put("Message"+i,u.getMessage());
            i++;
        }
        result.put("pages",pages);
        result.put("status","1");
        return result;
    }

    @RequestMapping(value="/returnSearch",method= RequestMethod.POST)
    @ResponseBody
    public Object returnSearch(HttpServletRequest request, HttpSession session)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");

        Map<String,Object> result=new HashMap<>();
        Map<String,Object> page=new HashMap<>();
        String num=request.getParameter("page");
        String name="%"+request.getParameter("name")+"%";
        System.out.println("page:"+num+"name:"+name);
        if(num==null){
            num="1";
        }
        page=FLKServiceImpl.findByNameLike(name,Integer.parseInt(num));
        int pages= (int) page.get("TotalPages");
        List<user> alluser= (List<user>) page.get("alluser");
        int i=1;
        for(user u:alluser){
            System.out.println(u.getLogname()+":"+u.getPic()+":"+u.getEmail()+":"+u.getMessage());
            result.put("Logname"+i,u.getLogname());
            result.put("Pic"+i,u.getPic());
            result.put("Email"+i,u.getEmail());
            result.put("Message"+i,u.getMessage());
            i++;
        }
        result.put("pages",pages);
        result.put("page",num);
        result.put("status","1");
        return result;
    }
    @RequestMapping(value="/followOne",method= RequestMethod.POST)
    @ResponseBody
    public Object followOne(HttpServletRequest request, HttpSession session)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");
        String follower = (String) session.getAttribute("logname");
        String befollower=request.getParameter("name");
        Map<String,Object> result=new HashMap<>();
        String ans = FLKServiceImpl.followOne(follower,befollower);
        if(ans.equals("1")){
            result.put("status","1");
        }else{
            result.put("status","0");
        }
        return result;
    }
    @RequestMapping(value="/ensurefollow",method= RequestMethod.POST)
    @ResponseBody
    public Object ensurefollow(HttpServletRequest request, HttpSession session)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");
        String follower = (String) session.getAttribute("logname");
        String befollower=request.getParameter("name");
        Map<String,Object> result=new HashMap<>();
        if(userfollowerRepository.findByu_logname_AndBu_Logname(follower,befollower)!=null){
            result.put("status","1");
        }else{
            result.put("status","0");
        }
        return result;
    }
    @RequestMapping(value = "/index")
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "hello";
    }

    public static String getSuffix(String fileName){
        int lastIndexOf = fileName.lastIndexOf(".");
        String suffix = fileName.substring(lastIndexOf + 1);
        return suffix;
    }
    @RequestMapping(value="/ocr",method= RequestMethod.POST)
    @ResponseBody
    public Object picOCR(@RequestParam("Idpicf") MultipartFile picOcr,@RequestParam("Idpicb") MultipartFile picOcr1) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");
        Map<String,Object> result=new HashMap<>();
        String logname= (String) session.getAttribute("logname");
        user u=userRepository.findBylogname(logname);
        if(!picOcr.isEmpty()){
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String base64 = base64Encoder.encode(picOcr.getBytes());
            base64 = base64.replaceAll("\r\n", "");
            base64 = base64.replaceAll("\\+", "%2B");
            String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token=" + BaiDuOCR.getAuth();
            //正面照
            String httpFront = "detect_direction=true&id_card_side=front&image=" + base64;
            //背面照
            String httpBack = "detect_direction=true&id_card_side=back&image=" + base64;
            String jsonResult = BaiDuOCR.request(httpUrl, httpFront);
            HashMap<String, String> map = BaiDuOCR.getHashMapByJson(jsonResult);
            for (String key : map.keySet()) {
                System.out.println(key + ": " + map.get(key));
                result.put(key,map.get(key));
            }
            byte [] bytes = picOcr.getBytes();
            String fileName=logname+"idcardforword.png";
            BufferedOutputStream bufferedOutputStream = new
                   BufferedOutputStream(new FileOutputStream(new File("F:\\java代码\\friendlooking\\src\\main\\resources\\userPic\\"+fileName)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
            u.setIdcardforward("../userPic/" +fileName);
        }else{
            System.out.println("forward"+"未上传");
        }
        if(!picOcr1.isEmpty()){
            BASE64Encoder base64Encoder1 = new BASE64Encoder();
            String base641 = base64Encoder1.encode(picOcr1.getBytes());
            base641 = base641.replaceAll("\r\n", "");
            base641 = base641.replaceAll("\\+", "%2B");
            String httpUrl1 = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token=" + BaiDuOCR.getAuth();
            //正面照
            String httpFront1 = "detect_direction=true&id_card_side=front&image=" + base641;
            //背面照
            String httpBack1 = "detect_direction=true&id_card_side=back&image=" + base641;
            String jsonResult1 = BaiDuOCR.request(httpUrl1, httpBack1);
            HashMap<String, String> map1 = BaiDuOCR.getHashMapByJson(jsonResult1);
            for (String key : map1.keySet()) {
                System.out.println(key + ": " + map1.get(key));
                result.put(key,map1.get(key));
            }
            byte [] bytes = picOcr1.getBytes();
            String fileName=logname+"idcardbackword.png";
            BufferedOutputStream bufferedOutputStream = new
                    BufferedOutputStream(new FileOutputStream(new File("F:\\java代码\\friendlooking\\src\\main\\resources\\userPic\\"+fileName)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
            u.setIdcardbackward("../userPic/" +fileName);
        }else {
            System.out.println("backward"+"未上传");
        }
        userRepository.save(u);
        return result;
    }
    @RequestMapping(value="/deleteMyinfo",method= RequestMethod.POST)
    @ResponseBody
    public Object deleteMyinfo(HttpServletRequest request, HttpSession session)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");
        String logname = (String) session.getAttribute("logname");
        Map<String,Object> result=new HashMap<>();
        if(!FLKServiceImpl.deleteMyinfo(logname).equals("")){
            session.removeAttribute("logname");
            result.put("status","done!");
        }
        return result;
    }

}
