package cn.kgc.text;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.kgc.Dao.UsersDao;
import cn.kgc.pogo.Users;

public class Text {
	
	public static void main(String[] args) {
		SqlSessionFactory  sqlSessionFactory=null;
		SqlSession  session =null;
		//创建读取器 读取映射文件
		try {
			Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
			//创建 能创建session的东西
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			
			sqlSessionFactory = builder.build(reader);
			//创建 session
			  session = sqlSessionFactory.openSession();
			
			//查询单个 select one  需要传的值是 sql的 映射文件 id
			//int result =session.selectOne("cn.kgc.Dao.UsersDao.getCount");
			  
			 // int result=session.getMapper(UsersDao.class).getCount();
			  List<Users>list=session.getMapper(UsersDao.class).getUsers();
			  for(Users user:list){
				  System.out.println(user);
			  }
			  
			  
			//System.out.println("总人数："+result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 关闭资源 先开的后关  后开的先关
			session.close();
		}
		
		
		
	}
}
