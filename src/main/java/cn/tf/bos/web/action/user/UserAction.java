package cn.tf.bos.web.action.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.web.action.BaseAction;

//用户管理
public class UserAction  extends  BaseAction  implements ModelDriven<User> {

	private User user=new User();
	
	public User getModel() {
		return user;
	}
	//修改密码
	public String editpassword(){
		User loginUser=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		user.setId(loginUser.getId());
		
		try {
			userService.editpwd(user);
			//修改成功
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("result", "success");
			map.put("msg","修改密码成功");
			ActionContext.getContext().put("map", map);
		} catch (Exception e) {
			//修改失败、
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("result", "failure");
			map.put("msg","修改密码失败"+e.getMessage());
			ActionContext.getContext().put("map", map);
		}
		return "editpassword";	
	}

}
