package com.rpg.das.repo;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import com.rpg.das.entity.User;
import com.rpg.utility.IOUtility;

/**
 * User repository
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserRepository implements IUserRepository {

	public static final String REPO_PATH="data/user/";
	private static final Path path = Paths.get(REPO_PATH);

	private static final HashMap<String, User> userData = new HashMap<>();
	private static final String SER = ".ser";

	static{
		try {
			if(!Files.exists(path))
				Files.createDirectories(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			File file=new File(REPO_PATH);
			String[] list = file.list();
			Arrays.stream(list).forEach(
					s->{
						ObjectInputStream input;
						try {
							Path path=Paths.get(REPO_PATH+s);
							input = new ObjectInputStream(Files.newInputStream(path));
							userData.put(s, (User) input.readObject());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveUser(User user){
		ObjectOutputStream out=null;
		try{
			Path path=Paths.get(REPO_PATH+user.getName()+SER);
			if(Files.exists(path)){
				Files.delete(path);
			}
			Path createFile = Files.createFile(path);
			out = new ObjectOutputStream(Files.newOutputStream(createFile));
			out.writeObject(user);
			out.flush();
			userData.put(user.getName()+SER, user);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(null!=out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public User findUserByName(String name){
		return userData.get(name+SER);
	}

	@Override
	public void deleteuser(User user) {
		User userFromDb = userData.get(user.getName()+SER);
		if(!userFromDb.getPwd().equalsIgnoreCase(user.getPwd())){
			IOUtility.writeOnConsole("user-id/pwd mismatch");
			return;
		}
		try{
			Path path=Paths.get(REPO_PATH+user.getName()+SER);
			if(Files.exists(path)){
				Files.delete(path);
			}
			userData.remove(user.getName()+SER);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public User findUserByNameAndPwd(User user) {
		User userFromDb = userData.get(user.getName()+SER);
		if(null==userFromDb)
			return null;
		else if(userFromDb.getPwd().equalsIgnoreCase(user.getPwd())){
			return userFromDb;
		}else
			return null;
	}
}
