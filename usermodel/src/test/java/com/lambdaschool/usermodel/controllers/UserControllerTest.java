package com.lambdaschool.usermodel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import com.lambdaschool.usermodel.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest
{

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private UserService userService;

   private List<User> userList;

   @Before
   public void setUp() throws Exception
   {
      userList = new ArrayList<>();

      Role r1 = new Role("admin");
      r1.setRoleid(1);
      Role r2 = new Role("user");
      r1.setRoleid(2);

      Role r3 = new Role("data");
      r1.setRoleid(3);

      // admin, data, user
      ArrayList<UserRoles> admins = new ArrayList<>();
      admins.add(new UserRoles(new User(),
              r1));
      admins.add(new UserRoles(new User(),
              r2));
      admins.add(new UserRoles(new User(),
              r3));
      User u1 = new User("test admin",
              "password",
              "admin@lambdaschool.local",
              admins);
      u1.setUserid(1);
      u1.getUseremails()
              .add(new Useremail(u1,
                      "admin@email.local"));
      u1.getUseremails()
              .add(new Useremail(u1,
                      "admin@mymail.local"));

      userList.add(u1);

      // data, user
      ArrayList<UserRoles> datas = new ArrayList<>();
      datas.add(new UserRoles(new User(),
              r3));
      datas.add(new UserRoles(new User(),
              r2));
      User u2 = new User("test cinnamon",
              "1234567",
              "cinnamon@lambdaschool.local",
              datas);
      u2.setUserid(2);
      u2.getUseremails()
              .add(new Useremail(u2,
                      "cinnamon@mymail.local"));
      u2.getUseremails()
              .add(new Useremail(u2,
                      "hops@mymail.local"));
      u2.getUseremails()
              .add(new Useremail(u2,
                      "bunny@email.local"));
      userList.add(u2);

      // user
      ArrayList<UserRoles> users = new ArrayList<>();
      users.add(new UserRoles(new User(),
              r2));
      User u3 = new User("test barnbarn",
              "ILuvM4th!",
              "barnbarn@lambdaschool.local",
              users);
      u3.setUserid(3);
      u3.getUseremails()
              .add(new Useremail(u3,
                      "barnbarn@email.local"));
      userList.add(u3);

      users = new ArrayList<>();
      users.add(new UserRoles(new User(),
              r2));
      User u4 = new User("test puttat",
              "password",
              "puttat@school.lambda",
              users);
      userList.add(u4);

      users = new ArrayList<>();
      users.add(new UserRoles(new User(),
              r2));
      User u5 = new User("test misskitty",
              "password",
              "misskitty@school.lambda",
              users);
      userList.add(u5);
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void listAllUsers() throws Exception
   {
      String apiUrl = "/restaurants/restaurants";
      Mockito.when(userService.findAll()).thenReturn(userList);

      RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

      MvcResult r = mockMvc.perform(rb).andReturn(); // this can throw an exception
      String tr = r.getResponse().getContentAsString();

      ObjectMapper mapper = new ObjectMapper();
      String er = mapper.writeValueAsString(userList);

      System.out.println("Expect: " + er);
      System.out.println("Actual: " + tr);

      assertEquals("Rest API Returns List", er, tr);
   }

   @Test
   public void getUserById()
   {
   }

   @Test
   public void getUserByName()
   {
   }

   @Test
   public void getUserLikeName()
   {
   }

   @Test
   public void addNewUser()
   {
   }

   @Test
   public void updateFullUser()
   {
   }

   @Test
   public void updateUser()
   {
   }

   @Test
   public void deleteUserById()
   {
   }

   @Test
   public void getNumUserEmails()
   {
   }

   @Test
   public void deleteUserRoleByIds()
   {
   }

   @Test
   public void postUserRoleByIds()
   {
   }
}