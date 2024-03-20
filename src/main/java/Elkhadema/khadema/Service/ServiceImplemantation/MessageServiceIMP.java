package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.List;
import java.util.stream.Collectors;

import Elkhadema.khadema.DAO.DAOImplemantation.MessageDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceInterfaces.MessageService;
import Elkhadema.khadema.domain.Message;
import Elkhadema.khadema.domain.MessageReceiver;
import Elkhadema.khadema.domain.User;

public class MessageServiceIMP implements MessageService {
	MessageDAO mdao= new MessageDAO();
	UserDAO uDao = new UserDAO();
	@Override
	public void sendMessage(User user, Message message) {
		MessageReceiver mr = new MessageReceiver(message, user, 0, 0);
		mdao.save(message, mr);
	}

	@Override
	public void deleteMessage(Message message) {
		mdao.delete(message);
	}

	@Override
	public List<Message> chat(User currentUser, User otherUser) {
		return mdao.getconversation(currentUser, otherUser).stream()
		.map(t -> {if(t.getSender().getId()==currentUser.getId()) {
				t.setSender(currentUser);
				}
				else {t.setSender(otherUser);
				}return t;
		}).collect(Collectors.toList());
	
		
	}

	@Override
	public List<Message> listOfChats(User user) {
		return mdao.getMessageByUserId(user.getId());
	}
	public List<User> getListoflastmessagers(User user){
		return mdao.getlistofChatsByUserId(user.getId()).stream().map(t -> uDao.get(t.getId()).get()).collect(Collectors.toList());
	}

}