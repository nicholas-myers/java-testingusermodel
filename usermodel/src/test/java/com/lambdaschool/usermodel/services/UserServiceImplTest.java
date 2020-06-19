package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest
{

   @Autowired
   private UserService userService;

   @Before
   public void setUp() throws Exception
   {
      MockitoAnnotations.initMocks(this);
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test()
   public void afindUserById()
   {
      assertEquals("test admin", userService.findUserById(4).getUsername());
   }

   @Test(expected = EntityNotFoundException.class)
   public void aafindUserByIdFail()
   {
      assertEquals("test admin", userService.findUserById(1).getUsername());
   }

   @Test
   public void bfindByNameContaining()
   {
      assertEquals(5, userService.findByNameContaining("test").size());
   }

//   @Test(expected = EntityNotFoundException.class)
//   public void bafindByNameContainingFails()
//   {
//      assertEquals(5, userService.findByNameContaining("blahblah").size());
//   }

   @Test
   public void cfindAll()
   {
      assertEquals(30, userService.findAll().size());
   }

   @Test
   public void ddelete()
   {
      userService.delete(4);
      assertEquals(29, userService.findAll().size());
   }

   @Test (expected = EntityNotFoundException.class)
   public void dadeletenotfound()
   {
      userService.delete(1);
      assertEquals(29, userService.findAll().size());
   }

   @Test
   public void efindByName()
   {
      assertEquals("test cinnamon", userService.findByName("test cinnamon").getUsername());
   }

   @Test(expected = EntityNotFoundException.class)
   public void eafindByNameFail()
   {
      assertEquals("the boss", userService.findByName("the boss").getUsername());
   }

   @Test
   public void fsave()
   {
      List<UserRoles> thisRole = new ArrayList<>();

      User newUser = new User("test new user",
              "testnewpassword",
              "testemail@test.com",
              thisRole);
      newUser.getUseremails().add(new Useremail(newUser,"2ndtestemail@test.com"));
      User addUser = userService.save(newUser);

      assertNotNull(addUser);
      User foundUser = userService.findUserById(addUser.getUserid());
      assertEquals(addUser.getUsername(), foundUser.getUsername());
   }

   @Test
   public void gupdate()
   {
      List<UserRoles> thisRole = new ArrayList<>();

      User newUser = new User("test edited user",
              "testnewpassword",
              "testemail@test.com",
              thisRole);
      newUser.getUseremails().add(new Useremail(newUser,"2ndtestemail@test.com"));
      User updateUser = userService.update(newUser, 11);
   }

   @Test
   public void hgetCountUserEmails()
   {
      assertEquals(28, userService.getCountUserEmails().size());
   }

   @Test
   public void ideleteUserRole()
   {
      userService.deleteUserRole(7, 3);
   }

   @Test
   public void jaddUserRole()
   {
      userService.addUserRole(7, 1);
   }
}