package com.ssm.chapter5.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ssm.chapter5.mapper.EmployeeMapper;
import com.ssm.chapter5.mapper.PdRoleMapper;
import com.ssm.chapter5.mapper.RoleMapper;
import com.ssm.chapter5.mapper2.RoleMapper2;
import com.ssm.chapter5.mapper2.UserMapper2;
import com.ssm.chapter5.param.PageParams;
import com.ssm.chapter5.param.PdCountRoleParams;
import com.ssm.chapter5.param.PdFindRoleParams;
import com.ssm.chapter5.param.RoleParams;
import com.ssm.chapter5.pojo.Employee;
import com.ssm.chapter5.pojo.Role;
import com.ssm.chapter5.pojo2.Role2;
import com.ssm.chapter5.pojo2.User2;
import com.ssm.chapter5.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class Chapter5Main {
	
	
	
	public static void main(String[] args) {
//		testPdCountRole();
////		testPdFindRole();
	}
	@Test
	public  void testGetRole() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRole(1L);
			System.out.println(role.getRoleName());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
@Test
	public void testFindRolesByMap() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("roleName", "1");
			parameterMap.put("note", "1");
			List<Role> roles = roleMapper.findRolesByMap(parameterMap);
			System.out.println(roles.size());
			System.out.println(roles);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testFindRolesByAnnotation() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<Role> roles = roleMapper.findRolesByAnnotation("1", "1");
			System.out.println(roles.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Test
	public void testFindRolesByBean() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			RoleParams roleParam = new RoleParams();
			roleParam.setNote("1");
			roleParam.setRoleName("1");
			List<Role> roles = roleMapper.findRolesByBean(roleParam);
			System.out.println(roles.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testFindByMix() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			RoleParams roleParam = new RoleParams();
			roleParam.setNote("n");
			roleParam.setRoleName("r");
			PageParams pageParams = new PageParams();
			pageParams.setStart(0);
			pageParams.setLimit(100);
			List<Role> roles = roleMapper.findByMix(roleParam, pageParams);
			System.out.println(roles.size());
			for (Role role : roles) {
				System.out.println(role);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testFindByRowBounds() {
		SqlSession sqlSession = null;
		try { sqlSession = SqlSessionFactoryUtils.openSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		RowBounds rowBounds = new RowBounds(0,20);
		List<Role> roleList = roleMapper.findByRowBounds("role_name", "note", rowBounds);
		System.err.println(roleList.size());
			System.err.println(roleList);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testGetRoleUseResultMap() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRoleUseResultMap(1L);
			System.out.println(role.getRoleName());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testInsertRole() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = new Role();
			role.setRoleName("role_name_5");
			role.setNote("note_5");
			roleMapper.insertRole(role);
			//����
			System.out.println(role.getId());
			sqlSession.commit();
		} catch(Exception ex) {
			sqlSession.rollback();
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testInsertRole2() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = new Role();
			role.setRoleName("role_name");
			role.setNote("note");
			roleMapper.insertRole2(role);
			//����
			System.out.println(role.getId());
			sqlSession.commit();
		} catch(Exception ex) {
			sqlSession.rollback();
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testUpdateRole() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = new Role();
			role.setRoleName("role_name_1_update");
			role.setNote("note_1_update");
			role.setId(1L);
			roleMapper.updateRole(role);
			sqlSession.commit();
		} catch(Exception ex) {
			sqlSession.rollback();
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testDeleteRole() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			roleMapper.deleteRole(3L);
			sqlSession.commit();
		} catch(Exception ex) {
			sqlSession.rollback();
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testGetEmployee() {
		SqlSession sqlSession = null;
		try {
			Logger logger = Logger.getLogger(Chapter5Main.class);
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = employeeMapper.getEmployee(1L);
			logger.info(employee.getBirthday());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testGetEmployee2() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = employeeMapper.getEmployee2(1L);
			System.out.println(employee.getWorkCard().getPosition());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testUserRole() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper2 roleMapper2 = sqlSession.getMapper(RoleMapper2.class);
			Role2 role2 = roleMapper2.getRole(1L);
			role2.getUserList();
			UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
			userMapper2.getUser(1L);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testOneLevelCache() {
		SqlSession sqlSession = null;
		Logger logger = Logger.getLogger(Chapter5Main.class);
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRole(1L);
			logger.info("........在获取一次POJO........");
			Role role2 = roleMapper.getRole(1L);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testOneLevelCache2() {
		SqlSession sqlSession = null;
		SqlSession sqlSession2 = null;
		Logger logger = Logger.getLogger(Chapter5Main.class);
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			sqlSession2 = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRole(1L);
			//需要提交，如果是一级缓存，MyBatis才会缓存对象到SqlSessionFactory层面
			sqlSession.commit();
			logger.info("不同sqlSession再获取一次POJO......");
			RoleMapper roleMapper2 = sqlSession2.getMapper(RoleMapper.class);
			Role role2 = roleMapper2.getRole(1L);
			//需要提交MyBatis才缓存对象到SQLSessionFactory
			sqlSession2.commit();
		} catch(Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
			if (sqlSession2 != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testPdCountRole() {
		PdCountRoleParams params = new PdCountRoleParams();
		SqlSession sqlSession = null;
		try {
			Logger logger = Logger.getLogger(Chapter5Main.class);
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			PdRoleMapper roleMapper = sqlSession.getMapper(PdRoleMapper.class);
			params.setRoleName("role_name");
			roleMapper.countRole(params);
			logger.info(params.getTotal());
			logger.info(params.getExecDate());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void testPdFindRole() {
		PdFindRoleParams params = new PdFindRoleParams();
		SqlSession sqlSession = null;
		try {
			Logger logger = Logger.getLogger(Chapter5Main.class);
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			PdRoleMapper roleMapper = sqlSession.getMapper(PdRoleMapper.class);
			params.setRoleName("role_name");
			params.setStart(0);
			params.setEnd(100);
			roleMapper.findRole(params);
			logger.info(params.getRoleList().size());
			logger.info(params.getTotal());
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
