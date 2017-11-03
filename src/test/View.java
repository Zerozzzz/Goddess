package test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import practice.dao.ExecuteGodess;
import practice.model.Godess;

public class View {

	private static final String MENU = "欢迎来到女神乐园\n"+
			"下面是菜单功能列表\n"
			+ "[MAIN/M]主菜单\n"
			+ "[QUERY/Q]查询全部女神信息\n"
			+ "[UPDATE/U]更新女神信息\n"
			+ "[ADD/A]添加女神\n"
			+ "[DEL/D]删除女神\n"
			+ "[GET/G]查询某位女神\n"
			+ "[SELECT/S]按条件查询女神信息\n"
			+ "[EXIT/E]退出程序\n"
			+ "[BACK/B]返回上一级菜单\n";
	
	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_DEL = "DEL";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_SELECT = "SELECT";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BACK = "BACK";
	
	public static void main(String[] args) {
		System.out.println(MENU);
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入需要执行的命令");

		String prenious = null;//通过给prenious赋值，确保一个命令执行完全
		Godess godess = new Godess();
		ExecuteGodess eg = new ExecuteGodess();
		Integer step = 1;//控制各个方法一步步执行
		//List、Map在按条件查询中使用
		List<Map<String,Object>> params = null;
		Map<String,Object> map = null;
		
		//循环执行命令，保证程序一直运行，直到
		while (scan.hasNext()) {
			String console = scan.next();
			
			if (OPERATION_EXIT.equals(console.toUpperCase()) 
					|| OPERATION_EXIT.substring(0, 1).equals(console.toUpperCase())) {
				System.out.println("您已经退出当前程序！");
				break;
			}else if (OPERATION_ADD.equals(console.toUpperCase()) 
					|| OPERATION_ADD.substring(0, 1).equals(console.toUpperCase() )
					|| OPERATION_ADD.equals(prenious)) {
				prenious = OPERATION_ADD;
				
				//返回上一级
				if (OPERATION_BACK.equals(console.toUpperCase()) 
						|| OPERATION_BACK.substring(0, 1).equals(console.toUpperCase())) {
					System.out.println("返回上一级！");
					prenious = null;//重置prenious,进入下一个命令
					step = 1;
					continue;
				}
				
				if (1 == step) {
					System.out.println("请输入女神的姓名：");
				}else if (2 == step) {
					godess.setUsername(console);
					System.out.println("请输入女神的年龄：");
				}else if (3 == step){
					godess.setAge(Integer.valueOf(console));
					System.out.println("请输入女神的性别：");
				}else if (4 == step) {
					godess.setSex(console);
					System.out.println("请输入女神的生日,格式为yyyy-mm-dd：");
				}else if (5 == step) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
					Date birthdat = null;
					try {
						birthdat = sdf.parse(console);
						godess.setBirthday(birthdat);
						System.out.println("请输入女神的邮箱：");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("您输入的日期有误，请从新输入");
						step = 4;
					}
				}else if (6 == step) {
					godess.setEmail(console);
					System.out.println("请输入女神的手机号：");
				}else if (7 == step) {
					godess.setMobile(console);
					eg.addGodess(godess);
					System.out.println("添加女神成功，按BACK/B返回上一级菜单，按EXIT/E退出");
					step = 0;//add方法結束后將step设为初始值，因下面有step自增，设为0
				}else {
					System.out.println("添加女神失败，按BACK/B返回上一级菜单，按EXIT/E退出");
					step = 0;//下面有step自增，设为0
				}
				
				//step自增，确保add方法一步步进行
				if (OPERATION_ADD.equals(prenious)) {
					step++;
				}
				 
			}else if (OPERATION_QUERY.equals(console.toUpperCase()) 
					|| OPERATION_QUERY.substring(0, 1).equals(console.toUpperCase())) {
				try {
					List<Godess> list = eg.query();
					for (Godess u : list) {
						System.out.printf("%-4d姓名：%-5s\n",u.getId(),u.getUsername());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}else if (OPERATION_GET.equals(console.toUpperCase()) 
					|| OPERATION_GET.substring(0, 1).equals(console.toUpperCase()) 
					|| OPERATION_GET.equals(prenious)) {
				
				prenious = OPERATION_GET;
				//返回上一级
				if (OPERATION_BACK.equals(console.toUpperCase()) 
						|| OPERATION_BACK.substring(0, 1).equals(console.toUpperCase())) {
					System.out.println("返回上一级！");
					prenious = null;//重置prenious,进入下一个命令
					step = 1;//add方法結束后將step设为初始值
					continue;
				}
				if (1 == step) {
					System.out.println("请输入需要查询女神的id");
				} else if (2 == step) {
					try {
						godess = eg.queOneGodess(Integer.valueOf(console));
						if (godess != null) {
							System.out.println(godess);
							System.out.println("继续查询请输入GET/G");
						} else {
							System.out.println("您输入的女神不存在");
							System.out.println("继续查询请输入GET/G");
						}
					} catch (NumberFormatException e) {
						System.out.println("您输入的id错误，请重新输入");
						e.printStackTrace();
						continue;
					} catch (SQLException e) {
						e.printStackTrace();
					}
					step = 0;//add方法結束后將step设为初始值，因下面
				}
				//step自增，确保get方法一步步进行
				if (OPERATION_GET.equals(prenious)) {
					step++;
				}
			}else if (OPERATION_SELECT.equals(console.toUpperCase()) 
					|| OPERATION_SELECT.substring(0, 1).equals(console.toUpperCase()) 
					|| OPERATION_SELECT.equals(prenious)) {
				prenious = OPERATION_SELECT;
				//返回上一级
				if (OPERATION_BACK.equals(console.toUpperCase()) 
						|| OPERATION_BACK.substring(0, 1).equals(console.toUpperCase())) {
					System.out.println("返回上一级！");
					prenious = null;//重置prenious,进入下一个命令
					step = 1;//add方法結束后將step设为初始值
					continue;
				}
				
				if (1 == step) {
					params = new ArrayList<Map<String,Object>>();
					map = new HashMap<>();
					System.out.println("请输入查找的条件名，比如：id、姓名、手机号");
				} else if (2 == step){
					map.put("name", console);
					System.out.println("输入匹配关系（\"=\"、\"like\"）");
				}else if (3 == step) {
					map.put("rela", console);
					System.out.println("请输入查找值,如果只为字符串请加单引号['string']");
				
				} else if (4 == step) {
					map.put("value", console);
					params.add(map);
					try {
						List<Godess> list = eg.query(params);
						if (list.isEmpty()) {
							System.out.println("没有找到符合条件的女神！");
						} else {
							for (Godess u : list) {
								System.out.println(u);
							}
						}
					} catch (SQLException e) {
						System.out.println("查询失败，请输入[SELECT/S]重新查询");
						e.printStackTrace();
					} finally {
						prenious = null;//重置prenious,进入下一个命令
						step = 1;//add方法結束后將step设为初始值,下面有step自增，设为0
					}
				}
				
				//step自增，确保get方法一步步进行
				if (OPERATION_SELECT.equals(prenious)) {
					step++;
				}
				
			}else if (OPERATION_UPDATE.equals(console.toUpperCase()) 
					|| OPERATION_UPDATE.substring(0, 1).equals(console.toUpperCase()) 
					|| OPERATION_UPDATE.equals(prenious)) {
				prenious = OPERATION_UPDATE;
				//返回上一级
				if (OPERATION_BACK.equals(console.toUpperCase()) 
						|| OPERATION_BACK.substring(0, 1).equals(console.toUpperCase())) {
					System.out.println("返回上一级！");
					prenious = null;//重置prenious,进入下一个命令
					step = 1;//add方法結束后將step设为初始值
					continue;
				}
				
				if (1 == step) {
					System.out.println("请输入需要更新的女神的id");
					
				} else if(2 == step) {
					Integer id = Integer.valueOf(console);
					try {
						Godess g = eg.queOneGodess(id);
						if(g == null) {
							System.out.println("您更新的女神不存在，请先添加");
							prenious = null;//重置prenious,进入下一个命令
							step = 1;//add方法結束后將step设为初始值
							continue;
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					godess.setId(id);
					System.out.println("请输入需要更新后女神的姓名");
				} else if (3 == step) {
					godess.setUsername(console);
					System.out.println("请输入需要更新后女神的性别");
				} else if (4 == step) {
					godess.setSex(console);
					System.out.println("请输入需要更新后女神的年龄");
				} else if (5 == step) {
					godess.setAge(Integer.valueOf(console));
					System.out.println("请输入需要更新后女神的生日");
				} else if (6 == step) {
					SimpleDateFormat sdp = new SimpleDateFormat("yyyy-mm-dd");
					Date birthday = null;
					try {
						birthday = sdp.parse(console);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					godess.setBirthday(birthday);
					System.out.println("请输入需要更新后女神的邮箱");
				} else if (7 == step) {
					godess.setEmail(console);
					System.out.println("请输入需要更新后女神的手机号");
				}else if (8 == step) {
					godess.setMobile(console);
					System.out.println("请输入更新者");
				}else if (9 == step) {
					godess.setUpdate_user(console);
					eg.upGodess(godess);
					System.out.println("更新女神成功\n"
							+ "按[BACK/B]返回上一级菜单");
				}
				
				//step自增，确保get方法一步步进行
				if (OPERATION_UPDATE.equals(prenious)) {
					step++;
				}
			}else if (OPERATION_DEL.equals(console.toUpperCase()) 
					|| OPERATION_DEL.substring(0, 1).equals(console.toUpperCase()) 
					|| OPERATION_DEL.equals(prenious)) {
				prenious = OPERATION_DEL;
				//返回上一级
				if (OPERATION_BACK.equals(console.toUpperCase()) 
						|| OPERATION_BACK.substring(0, 1).equals(console.toUpperCase())) {
					System.out.println("返回上一级！");
					prenious = null;//重置prenious,进入下一个命令
					step = 1;//add方法結束后將step设为初始值
					continue;
				}
				
				if (1 == step) {
					System.out.println("请输入您想删除的女神id");
				} else if (2 == step) {
					eg.delGodess(Integer.valueOf(console));
					System.out.println("女神删除成功,按[BACK/B]返回上一级菜单");
				}
				
				//step自增，确保get方法一步步进行
				if (OPERATION_DEL.equals(prenious)) {
					step++;
				}
				
			}else if (OPERATION_MAIN.equals(console.toUpperCase()) 
					|| OPERATION_MAIN.substring(0, 1).equals(console.toUpperCase())) {
//				prenious = null;//重置prenious,进入下一个命令
//				step = 1;//add方法結束后將step设为初始值
				System.out.println(MENU);
			}
		}
		scan.close();
	}

}
