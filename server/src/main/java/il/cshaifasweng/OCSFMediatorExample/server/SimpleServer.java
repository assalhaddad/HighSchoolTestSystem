package il.cshaifasweng.OCSFMediatorExample.server;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import java.io.IOException;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class SimpleServer extends AbstractServer {
	private static Session session;
	private static SessionFactory sessionFactory = getSessionFactory();
	public SimpleServer(int port) {
		super(port);
	}
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Subject.class);
		configuration.addAnnotatedClass(Message.class);
		configuration.addAnnotatedClass(Teacher.class);
		configuration.addAnnotatedClass(Principal.class);
		configuration.addAnnotatedClass(Exam.class);
		configuration.addAnnotatedClass(Course.class);
		configuration.addAnnotatedClass(Request.class);


		ServiceRegistry serviceRegistry = (new StandardServiceRegistryBuilder()).applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	ArrayList<Student> studentsList = new ArrayList();
	ArrayList<Teacher> teachersList = new ArrayList();
	ArrayList<Question> questions = new ArrayList();
	ArrayList<Subject> subjects = new ArrayList();
	ArrayList<Request> requests = new ArrayList();
	Principal principal;
	public void generateStudents(){
		Student student=new Student("123456781","Assal Haddad", "assalHaddad","assal123");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456782","Ayleen Monayer","ayleenMonayer","ayleen55");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456783","Aseel Nahhas","aseelNahhas","aseelN34");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456784","Nawal Khoury","nawalKhoury","nawal09");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456785","Tony Hayek","tonyHayek","tonyH4");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456786","Bshara Khoury","bsharaKhoury","bshara88");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456787","Aseel Shaheen","aseelShaheen","aseelS7");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456788","Victor Hanhan","victorHanhan","victor33");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456789","Amal Sharif","amalSharif","amal92");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456790","Leen Salman","leenSalman","leen8822");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456791","Elie Monayer","elieMonayer","elie88");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456792","Adam Levi","adamLevi","adam111");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456793","Inbar Kakon","inbarKakon","inbar83");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456794","Yair Lapid","yairLapid","yair92");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456795","Fadi Zahra","fadiZahra","fadi67");
		studentsList.add(student);
		session.save(student);
		session.flush();
		student=new Student("123456796","Rojeh Tannous","rojehTannous","rojeh81");
		studentsList.add(student);
		session.save(student);
		session.flush();
	}

		public void generateTeachers(){
			Teacher teacher = new Teacher("Shir Sneh","shirSneh","shir132");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
			teacher = new Teacher("Nataly Monayer","natalyMonayer","nataly78");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
			teacher = new Teacher("Or Meir","orMeir","or88");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
			teacher = new Teacher("Moran Feldman","moranFeldman","moran2");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
			teacher = new Teacher("Norit Cohen","noritCohen","norit77");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
			teacher = new Teacher("Bill Gates","billGates","bill5");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
			teacher = new Teacher("Taylor Swift","taylorSwift","taylor66");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
			teacher = new Teacher("Merav Michaeli","meravMichaeli","merav46");
			teachersList.add(teacher);
			session.save(teacher);
			session.flush();
		}

		public void generateQuestions() {
			Question question = new Question("38754","How much is 3*1?","4","19","3","9",3,subjects.get(0));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("83668","How much is 27+13?","40","42","67","80",1,subjects.get(0));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("13794","Complete the sentence:"+"\n"+"Where ____ the dog?","are","it","is","does",3,subjects.get(1));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("82640","Complete the sentence:"+"\n"+"I want ___ help you","not","at","on","to",4,subjects.get(1));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("86490","Complete the sentence:"+"\n"+"I am going to play soccer ___ my friends","with","on","in","are",1,subjects.get(1));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("73956","How much is 90-12?","78","88","65","79",1,subjects.get(0));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("92745","Which animal lays eggs?","Dog","Cat","Duck","Sheep",3,subjects.get(2));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("85614","A male cow is called?","Ox","Monkey","Lion","Sheep",1,subjects.get(2));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("12946","Complete the sentence:"+"\n"+"All animals need food, air and ___ to survive","candy","phone","water","fruits",3,subjects.get(2));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("92748","What part of the body helps you move?","Eyes","Lungs","Muscles","Fingers",3,subjects.get(2));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("34509","Complete the sentence:"+"\n"+"She is coming ___","yesterday","tomorrow","not","last week",2,subjects.get(1));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("13975","How much is 27/9?","2","5","6","3",4,subjects.get(0));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("98723","The two holes of the nose are called?","Eyelids","Nostrils","Nails","Hair",2,subjects.get(2));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("72640","Which is the largest country in the world?","Russia","Canada","Ukraine","Mexico",1,subjects.get(3));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("67824","Which country has the largest population in the world?","Russia","America","China","Brazil",3,subjects.get(3));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("12987","How many states of India share its border with Bhutan?","1","2","3","4",4,subjects.get(3));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("35600","What is the capital city of India?","Chennai","New Delhi","Mumbai","Bangalore",2,subjects.get(3));
			questions.add(question);
			session.save(question);
			session.flush();
			question = new Question("56091","In which country would you find the Leaning Tower of Pisa?","Italy","Amsterdam","Israel","France",1,subjects.get(3));
			questions.add(question);
			session.save(question);
			session.flush();
		}
		public void generateSubjects(){
			Subject subject = new Subject("Math");
			subjects.add(subject);
			session.save(subject);
			session.flush();
			subject = new Subject("English");
			subjects.add(subject);
			session.save(subject);
			session.flush();
			subject = new Subject("Science");
			subjects.add(subject);
			session.save(subject);
			session.flush();
			subject = new Subject("Geography");
			subjects.add(subject);
			session.save(subject);
			session.flush();
		}
		public void generatePrincipal(){
			principal = new Principal("Malki Grosman","malkiGrosman","theprincipal1");
			session.save(principal);
			session.flush();
		}
		public void connectToData() {
			try {
				SessionFactory sessionFactory = getSessionFactory();
				session = sessionFactory.openSession();
				session.beginTransaction();
				generateSubjects();
				generateStudents();
				generateTeachers();
				generateQuestions();
				generatePrincipal();
				session.getTransaction().commit();
				//generateExams();
			} catch (Exception var5) {
				if (session != null) {
					session.getTransaction().rollback();
				}
				System.err.println("An error occured, changes have been rolled back.");
				var5.printStackTrace();
			} finally {
				session.close();
				session.getSessionFactory().close();
			}
		}
		Question question = new Question();
		Request requestExtraTime = new Request();

		@Override
		protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
			Message message = (Message)msg;
			String request = message.getMessage();
			System.out.println("insidehandleMessageFromClient");
			try {
				if (request.isBlank()) {
					message.setMessage("Error! we got an empty message");
					client.sendToClient(message);
				} else {
					if(request.equals("get list of subjects")){
						System.out.println("inside get list of subjects");
						session=sessionFactory.openSession();
						session.beginTransaction();
						ArrayList<String> subjectList = new ArrayList<String>(subjects.size());
						for(int i=0; i<subjects.size(); i++)
							subjectList.add(i, subjects.get(i).getName());
						client.sendToClient(new Message("subjects list is ready", subjectList));
						session.close();
					}
					else if(request.equals("new question")){
						session=sessionFactory.openSession();
						session.beginTransaction();
						question.copy((Question)message.getObject());
						System.out.println(question.getText());
						questions.add(question);
						session.save(question);
						session.flush();
						client.sendToClient(new Message("question added successfully",(Object)null));
						session.close();
					}
					else if(request.equals("get list of students")){
						session=sessionFactory.openSession();
						session.beginTransaction();
						ArrayList<Student> students = new ArrayList<Student>(studentsList.size());
						for(int i=0; i<studentsList.size(); i++)
							students.add(i, studentsList.get(i));
						client.sendToClient(new Message("students list is ready", students));
						session.close();
					}
					else if(request.equals("get list of teachers")){
						session=sessionFactory.openSession();
						session.beginTransaction();
						ArrayList<Teacher> teachers = new ArrayList<Teacher>(teachersList.size());
						for(int i=0; i<teachersList.size(); i++)
							teachers.add(i, teachersList.get(i));
						client.sendToClient(new Message("teachers list is ready", teachers));
						session.close();
					}
					else if(request.equals("get the principal")){
						session=sessionFactory.openSession();
						session.beginTransaction();
						client.sendToClient(new Message("principal is ready", principal));
						session.close();
					}
					else if(request.equals("new extra time request")){
						session=sessionFactory.openSession();
						session.beginTransaction();
						requestExtraTime.copy((Request)message.getObject());
						requests.add(requestExtraTime);
						session.save(requestExtraTime);
						session.flush();
						client.sendToClient(new Message("request added successfully",(Object)null));
						session.close();
					}
					else if(request.equals("get list of requests")){
						session=sessionFactory.openSession();
						session.beginTransaction();
						ArrayList<Request> requests1 = new ArrayList<Request>();
						for(int i=0; i<requests.size(); i++){
							if(!requests.get(i).getIsDone())
								requests1.add(i, requests.get(i));
						}
						client.sendToClient(new Message("requests list is ready", requests1));
						session.close();
					}
					else if(request.equals("approve this request")){
						session=sessionFactory.openSession();
						session.beginTransaction();
						requestExtraTime.copy((Request)message.getObject());
						requests.remove(requestExtraTime);
						session.remove(requestExtraTime);
						requestExtraTime.copy((Request)message.getObject());
						requestExtraTime.setIsDone();
						requests.add(requestExtraTime);
						session.save(requestExtraTime);
						session.flush();
						client.sendToClient(new Message("request approved successfully",(Object)null));
						session.close();
					}
					session.flush();
					session.close();
				}
			} catch (IOException var13) {
				var13.printStackTrace();
			}

		}
	}