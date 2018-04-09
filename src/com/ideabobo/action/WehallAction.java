package com.ideabobo.action;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.*;

import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ideabobo.model.Bill;
import com.ideabobo.model.Dingzuo;
import com.ideabobo.model.Good;
import com.ideabobo.model.Posts;
import com.ideabobo.model.Replay;
import com.ideabobo.model.User;
import com.ideabobo.service.BaseService;
import com.ideabobo.service.BillService;
import com.ideabobo.service.DingzuoService;
import com.ideabobo.service.GoodService;
import com.ideabobo.service.PostsService;
import com.ideabobo.service.ReplayService;
import com.ideabobo.service.ShopService;
import com.ideabobo.service.TypeService;
import com.ideabobo.service.UserService;
import com.ideabobo.util.GetNowTime;
import com.ideabobo.util.HttpClientTools;
import com.ideabobo.util.IdeaAction;

@Controller
public class WehallAction extends IdeaAction {
	@Resource
    private BaseService baseService;
    @Resource
    private BillService billService;
    @Resource
    private ShopService shopService;
    @Resource
    private GoodService goodService;
    @Resource
    private DingzuoService dingzuoService;
    @Resource
    private TypeService typeService;
    @Resource
    private UserService userService;
    @Resource
    private PostsService postsService;
    @Resource
    private ReplayService replayService;
    public Gson gson = new Gson();
    private static final long serialVersionUID = -3218238026025256103L;

    public String wehall(){
//		String openid = request.getParameter("openid");
//		session.put("openid", openid);
        return SUCCESS;
    }

    public void login(){
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        User user = new User();
        user.setPasswd(passwd);
        user.setUsername(encodeGet(username));
        User r = userService.find(user);
        if(r!=null){
            renderJsonpObj(r);
        }else{
            renderJsonpString("fail");
        }
    }

    public void checkUser(){
        User u = new User();
        String username = request.getParameter("username");
        u.setUsername(username);
        User r = userService.find(u);
        if(r!=null){
            renderJsonpString("fail");
        }else{
            renderJsonpString("success");
        }
    }

    public void updateUser(){
        String tel = request.getParameter("tel");
        String qq = request.getParameter("qq");
        String wechat = request.getParameter("wechat");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String sex = request.getParameter("sex");
        String id = request.getParameter("id");

        User user = userService.find(id);

        user.setId(Integer.parseInt(id));
        user.setTel(tel);
        user.setWechat(wechat);
        user.setQq(qq);
        user.setEmail(email);
        user.setBirth(birth);
        user.setSex(encodeGet(sex));

        userService.update(user);
        renderJsonpString("success");
    }

    public void changePasswd(){
        String passwd = request.getParameter("passwd");
        String id = request.getParameter("id");
        User user = userService.find(id);
        user.setPasswd(passwd);
        userService.update(user);
        renderJsonpString("success");
    }

    public void register(){
    	User user = (User) getByRequest(new User(), true);
    	user.setRoletype("2");
        userService.save(user);

        renderJsonpString("success");
    }

    public void listShop(){
        renderJsonpObj(shopService.list());
    }
    public void listGood(){
        String type = request.getParameter("stype");
        String sid = request.getParameter("sid");
        String btype = request.getParameter("type");
        Good g = new Good();
        if (type != null&& !"".equals(type)) {
            g.setTypeid(type);

        }
        if (btype != null&& !"".equals(btype)) {
            g.setBtype(btype);

        }
        if(sid != null&& !"".equals(sid)){
            g.setSid(sid);
        }
        renderJsonpObj(goodService.list(g));
    }

    public void listType(){
        renderJsonpObj(typeService.list());
    }

    public void saveDingzuo(){
        Dingzuo dz = new Dingzuo();
        dz.setRenshu(request.getParameter("renshu"));
        dz.setXingming(encodeGet(request.getParameter("xingming")));
        dz.setShouji(request.getParameter("shouji"));
        dz.setShijian(request.getParameter("shijian"));
        dz.setTodate(request.getParameter("todate"));
        dz.setBeizhu(encodeGet(request.getParameter("beizhu")));
        dz.setShopid(request.getParameter("shopid"));
        dz.setShopname(encodeGet(request.getParameter("shopname")));
        dz.setOpenid(request.getParameter("openid"));
        dz.setNdate(GetNowTime.getNowTimeNian());
        dingzuoService.save(dz);
        renderJsonpString("提交成功!");
    }

    public void saveBill(){
        Bill bill = new Bill();
        bill.setTotal(request.getParameter("total"));
        bill.setSid(request.getParameter("sid"));
        bill.setShop(encodeGet(request.getParameter("shop")));
        bill.setUid(request.getParameter("uid"));
        bill.setUser(encodeGet(request.getParameter("user")));
        bill.setNdate(GetNowTime.getNowTimeNian());
        bill.setGids(request.getParameter("gids"));
        bill.setGnames(encodeGet(request.getParameter("gnames")));
        bill.setAddress(encodeGet(request.getParameter("address")));
        bill.setTel(request.getParameter("tel"));
        bill.setNote(encodeGet(request.getParameter("note")));
        billService.save(bill);
        renderJsonpString("提交成功!");

    }

    public void saveBills(){
        String bills = request.getParameter("bills");
        bills = encodeGet(bills);
        JsonParser parser = new JsonParser();
        JsonArray blist = parser.parse(bills).getAsJsonArray();
        for(int i=0;i<blist.size();i++){
            JsonElement jo = blist.get(i);
            JsonObject obj = (JsonObject) parser.parse(jo.toString());
            Bill bill = new Bill();
            bill.setTotal(obj.get("total").getAsString());
            bill.setSid(obj.get("sid").getAsString());
            bill.setShop(obj.get("shop").getAsString());
            bill.setUid(obj.get("uid").getAsString());
            bill.setUser(obj.get("user").getAsString());
            bill.setNdate(GetNowTime.getNowTimeNian());
            bill.setGids(obj.get("gids").getAsString());
            bill.setGnames(obj.get("gnames").getAsString());
            bill.setAddress(obj.get("address").getAsString());
            bill.setTel(obj.get("tel").getAsString());
            bill.setNote(obj.get("note").getAsString());
            billService.save(bill);
        }

        renderJsonpString("提交成功!");
    }

    public void mybills(){
        String uid = request.getParameter("uid");
        Bill b = new Bill();
        b.setUid(uid);
        renderJsonpObj(billService.list(b));
    }
    public void myshopbills(){
        String sid = request.getParameter("sid");
        Bill b = new Bill();
        b.setSid(sid);
        renderJsonpObj(billService.list(b));
    }
    public Bill updateXiaoliang(Bill bill){
        String billstr = bill.getBill();
        JsonParser parser = new JsonParser();
        JsonArray ja = parser.parse(billstr).getAsJsonArray();
        String gnames = "";
        for(int i=0;i<ja.size();i++){
            JsonElement jo = ja.get(i);
            JsonObject obj = (JsonObject) parser.parse(jo.toString());
            String id = obj.get("id").getAsString();
            Good g = goodService.find(id);
            int count = Integer.parseInt(g.getCount());
            int bcount = Integer.parseInt(obj.get("count").getAsString());
            int total = count+bcount;
            g.setCount(total+"");
            if(i == 0){
                gnames+=obj.get("gname").getAsString();
            }else{
                gnames+=","+obj.get("gname").getAsString();
            }
            bill.setGnames(gnames);
            goodService.update(g);
        }
        return bill;
    }

    public void deleteGood(){
        String id = request.getParameter("id");
        goodService.delete(Integer.parseInt(id));
        renderJsonpString("success");
    }
    public void deleteBill(){
        String id = request.getParameter("id");
        billService.delete(Integer.parseInt(id));
        renderJsonpString("success");
    }
    
    public void addPosts(){
    	String uid = request.getParameter("uid");
    	String title = encodeGet(request.getParameter("title"));
    	String note = encodeGet(request.getParameter("note"));
    	String username = encodeGet(request.getParameter("username"));
    	String type = encodeGet(request.getParameter("type"));
    	String img = encodeGet(request.getParameter("img"));
    	String ndate = GetNowTime.getNowTimeEn();
    	Posts p = new Posts();
    	p.setUid(uid);
    	p.setTitle(title);
    	p.setUsername(username);
    	p.setNote(note);
    	p.setNdate(ndate);
    	p.setType(type);
    	p.setImg(img);
    	postsService.save(p);
    	renderJsonpString("success");
    }
    public void listPosts(){
        renderJsonpObj(postsService.list());
    }
    public void listReplay(){
    	String pid = request.getParameter("pid");
    	Replay r = new Replay();
    	r.setPid(pid);
        renderJsonpObj(replayService.list(r));
    }
    public void deletePosts(){
    	String id = request.getParameter("id");
    	postsService.delete(Integer.parseInt(id));    	
    	renderJsonpString("success");
    }
    public void addReplay(){
    	String uid = request.getParameter("uid");
    	String pid = request.getParameter("pid");
    	String note = encodeGet(request.getParameter("note"));
    	String username = encodeGet(request.getParameter("username"));
    	String ndate = GetNowTime.getNowTimeEn();
    	Replay m = new Replay();
    	m.setUid(uid);
    	m.setPid(pid);
    	m.setUsername(username);
    	m.setNote(note);
    	m.setNdate(ndate);
    	replayService.save(m);
    	renderJsonpString("success");
    }
    
    public void saveGood(){
    	Good info = (Good) getByRequest(new Good(), true);
    	String action = request.getParameter("action");
    	if(action.equals("edit")){
    		baseService.update(info);
    	}else{
    		baseService.save(info);
    	}
    	
        renderJsonpString("success");
    }
    
    public void delPosts(){
    	String id = request.getParameter("id");
    	baseService.delete(Integer.parseInt(id), Posts.class);
    	renderJsonpString("success");
    }
    
    public void listNotice(){
    	renderJsonpObj(baseService.list("from Notice"));
    }
    
    public void delGood(){
    	String id = request.getParameter("id");
    	baseService.delete(Integer.parseInt(id), Good.class);
    	renderJsonpString("success");
    }
    
    public void zan(){
    	String id = request.getParameter("id");
    	Good g = (Good) baseService.find(Integer.parseInt(id), Good.class);
    	Integer zan = g.getZan();
    	if(zan!=null){
    		zan++;
    	}else{
    		zan=1;
    	}
    	g.setZan(zan);
    	baseService.update(g);
    	renderJsonpString(zan+"");
    }
    
    public void getWeather(){
    	try {
    		String city = request.getParameter("city");
    		city = encodeGet(city);
			city = URLEncoder.encode(city,"utf-8");
			String url = "http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak=W6eQXHTZjNf7QTG9k9Mpboez";		
			String r = HttpClientTools.get(url);
		
			renderJsonpString(r);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetPasswd(){
    	String username = request.getParameter("username");
    	String email = request.getParameter("email");
    	String passwd = request.getParameter("passwd");
    	List ulist = baseService.list("from User t where t.username='"+username+"' and t.email='"+email+"'");
    	if(ulist!=null && ulist.size()>0){
    		User u = (User) ulist.get(0);
    		u.setPasswd(passwd);
    		baseService.update(u);
    		renderJsonpString("0");
    	}else{
    		renderJsonpString("-1");
    	}
    	
    }
}
