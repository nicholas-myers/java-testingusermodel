package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
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
   }

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
      assertEquals("test admin", userService.findByName("test admin").getUsername());
   }

   @Test(expected = EntityNotFoundException.class)
   public void eafindByNameFail()
   {
      assertEquals("the boss", userService.findByName("the boss").getUsername());
   }

   @Test
   public void fsave()
   {
   }

   @Test
   public void gupdate()
   {
   }

   @Test
   public void hgetCountUserEmails()
   {
      assertEquals(2, userService.findUserById(4).getUseremails().size());
   }

   @Test
   public void ideleteUserRole()
   {
   }

   @Test
   public void jaddUserRole()
   {
   }
}