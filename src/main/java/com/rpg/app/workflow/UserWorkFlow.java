package com.rpg.app.workflow;

import java.util.List;

import com.rpg.das.entity.UserGame;
import com.rpg.das.entity.GameCharacter;
import com.rpg.das.entity.User;
import com.rpg.game.IGame;
import com.rpg.game.adapter.GameAdapter;
import com.rpg.service.IUserService;
import com.rpg.service.impl.UserServiceImpl;
import com.rpg.utility.IOUtility;
import com.rpg.utility.StringUtility;

/**
 * User Work flow
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserWorkFlow {

	private IUserService userService=null;

	public UserWorkFlow(){
		userService=new UserServiceImpl();
	}

	public User createUser() {
		boolean flag=false;
		String name=null;
		do{
			IOUtility.writeOnConsole("Please Enter User Name : \n");
			name = IOUtility.readLine();
			flag=userService.checkIfNameTaken(name);
			if(flag){
				IOUtility.writeOnConsole("User Name already Taken, try new name\n");
			}
		}while(flag || StringUtility.isEmpty(name));
		String pwd=null;
		do{
			IOUtility.writeOnConsole("Enter Password : ");
			pwd = IOUtility.readLine(); 
		}while(StringUtility.isEmpty(pwd));
		IOUtility.writeOnConsole("Create Player \n");
		IOUtility.writeOnConsole("Please Choose Game\n");
		List<IGame> games = GameAdapter.listOfGames();
		StringBuilder sb= new StringBuilder();
		for (IGame game : games) {
			sb.append("Please choose : ");
			sb.append(game.getId());
			sb.append(" for ");
			sb.append(game.getName());
			sb.append("\n");
		}
		sb.append(":");
		int selectedGameId=0;
		do{
			IOUtility.writeOnConsole(sb.toString());
			try{
				selectedGameId = Integer.parseInt(IOUtility.readLine());
			}catch(NumberFormatException ex){
			}
		}while(selectedGameId<1 || selectedGameId>games.size());
		IGame game=GameAdapter.getGameByid(selectedGameId-1);
		GameCharacter character=game.createNewCharacterFlow(selectedGameId-1);

		User user = new User(name, pwd);
		UserGame gameSelected=new UserGame(selectedGameId);
		gameSelected.addCharacters(character);
		user.addGames(gameSelected);
		System.out.println();
		userService.saveUser(user);
		System.out.println("User Created successsfully");
		return user;
	}

	public User loginUser() {
		IOUtility.writeOnConsole("Not implemented, quiting now !");
		return null;
	}

}
