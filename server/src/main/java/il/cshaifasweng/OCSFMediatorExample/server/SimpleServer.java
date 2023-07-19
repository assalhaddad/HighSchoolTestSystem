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
		configuration.addAnnotatedClass(StudentData.class);
		configuration.addAnnotatedClass(SolvedExam.class);


		ServiceRegistry serviceRegistry = (new StandardServiceRegistryBuilder()).applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	ArrayList<Student> studentsList = new ArrayList();
	ArrayList<Teacher> teachersList = new ArrayList();
	ArrayList<Question> questions = new ArrayList();
	ArrayList<Subject> subjects = new ArrayList();
	ArrayList<Course> courses = new ArrayList();
	ArrayList<Exam> exams = new ArrayList();
	ArrayList<StudentData> studentDataList = new ArrayList();
	ArrayList<SolvedExam> solvedExamList = new ArrayList();
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

	public void generateStaff(){
		ArrayList<Subject> list = new ArrayList();
		list.add(subjects.get(0)); // Math
		list.add(subjects.get(1)); // English
		list.add(subjects.get(2)); // Science
		list.add(subjects.get(3)); // Geography
		Teacher teacher = new Teacher("Shir Sneh","shirSneh","shir132");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		currentTeacher.copy(teachersList.get(0)); // for example
		session.save(teacher);
		session.flush();
		teacher = new Teacher("Nataly Monayer","natalyMonayer","nataly78");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		teacher = new Teacher("Or Meir","orMeir","or88");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		teacher = new Teacher("Moran Feldman","moranFeldman","moran2");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		teacher = new Teacher("Norit Cohen","noritCohen","norit77");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		teacher = new Teacher("Bill Gates","billGates","bill5");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		teacher = new Teacher("Taylor Swift","taylorSwift","taylor66");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		teacher = new Teacher("Merav Michaeli","meravMichaeli","merav46");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		//Principal principal = new Principal("Malki Grosman","malkiGrosman","thePrinciple1");
		//session.save(principal);
		//session.flush();
	}

	public void generateQuestions() {
		ArrayList<Course> list = new ArrayList();
		list.add(courses.get(0)); // Basic Math
		Question question = new Question("14601","How much is 3*1?","4","19","3","9",3,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("13901","How much is 27/9?","2","5","6","3",4,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("83601","How much is 27+13?","40","42","67","80",1,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("73901","How much is 90-12?","78","88","65","79",1,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.add(courses.get(1));	// Advanced + Basic Math
		question = new Question("93601","What is the square root of 121?","12","8","11","10",3,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.clear();
		list.add(courses.get(1));	// Advanced Math
		question = new Question("78601","Which of the following is the equivalent of 13/25?","0.38","0.4","0.48","0.52",4,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("74201","Solve for x: -2(x+3)=8","x=0","x=-7","x=6","x=-4",2,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("64301","Solve for x: 3x+90=180","x=40","x=10","x=30","x=25",3,subjects.get(0),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.clear();
		list.add(courses.get(2)); // Basic English
		question = new Question("13702","Where ____ the dog?","are","it","is","does",3,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("34502","She is coming ___","yesterday","tomorrow","not","last week",2,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("82602","I want ___ help you","not","at","on","to",4,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("86402","I am going to play soccer ___ my friends","with","on","in","are",1,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.add(courses.get(3));	// Advanced + Basic English
		question = new Question("66602","George is doing an additional ____ in graphic design.","research","practice","course","study",3,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.clear();
		list.add(courses.get(3)); // Advanced English
		question = new Question("64302","Janet and James had a blazing ____ last night.","quarrel","debate","row","disagreement",3,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("64302","All conference participants wore ____ with their names.","badges","stickers","labels","signs",1,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("68302","When we saw a policeman we cleared ____ as fast as we could.","out","off","away","up",2,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("68302"," A deluge is the same as a ____.","downpour","blizzard","hailstorm","meltdown",1,subjects.get(1),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.clear();
		list.add(courses.get(4)); // Basic Science
		question = new Question("92703","Which animal lays eggs?","Dog","Cat","Duck","Sheep",3,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("85603","A male cow is called?","Ox","Monkey","Lion","Sheep",1,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("12903","All animals need food, air and ___ to survive","candy","phone","water","fruits",3,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("92703","What part of the body helps you move?","Eyes","Lungs","Muscles","Fingers",3,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("98703","The two holes of the nose are called?","Eyelids","Nostrils","Nails","Hair",2,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.add(courses.get(5)); // Advanced + Basic Science
		question = new Question("37103","What planet is closest to Earth?","Venus","Mars","Saturn","Jupiter",1,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.clear();
		list.add(courses.get(5)); // Advanced Science
		question = new Question("93103","Which plastic cannot be breakable?","Thermo","Thermosetting","Heated","Pasteurized",2,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("72303","What is the chemical name of sulphuric acid?","NaCl","H2O","H2SO4","CO2",3,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("14503","Which metal can be cut easily with a knife?","Sodium","Gold","Argentium","Ferrous",1,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("38503","Which non metal is very reactive on room temperature?","Phosporus","Carbon","Porus","Oxygen",1,subjects.get(2),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.clear();
		list.add(courses.get(6)); // Basic Geography
		question = new Question("72604","Which is the largest country in the world?","Russia","Canada","Ukraine","Mexico",1,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("67804","Which country has the largest population in the world?","Russia","America","China","Brazil",3,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("12904","How many states of India share its border with Bhutan?","1","2","3","4",4,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("35604","What is the capital city of India?","Chennai","New Delhi","Mumbai","Bangalore",2,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("21704","In which country would you find the Leaning Tower of Pisa?","Italy","Amsterdam","Israel","France",1,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.add(courses.get(7));	// Advanced + Basic Geography
		question = new Question("16304","How many States does the United States consist of?","48","49","50","51",3,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		list.clear();
		list.add(courses.get(7)); // Advanced Geography
		question = new Question("73404","What is the name of the tallest mountain in the world?","Mount Carmel","Mount Logan","Mount Everest","Mount Elbrus",3,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("45704","What is the name of the longest river in the world?","Amazon","Yellow","Congo","Nile",4,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("64204","What is the capital of Canada?","Montreal","Toronto","Ottawa","Vancouver",3,subjects.get(3),list);
		questions.add(question);
		session.save(question);
		session.flush();
		question = new Question("46404","What is the name of the smallest country in the world?","Monaco","The Vatican City","Tuvalu","Maldives",2,subjects.get(3),list);
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

	public void generateCourses(){
		Course course = new Course("Basic Math", subjects.get(0));
		courses.add(course);
		session.save(course);
		session.flush();
		course = new Course("Advanced Math", subjects.get(0));
		courses.add(course);
		session.save(course);
		session.flush();
		course = new Course("Basic English", subjects.get(1));
		courses.add(course);
		session.save(course);
		session.flush();
		course = new Course("Advanced English", subjects.get(1));
		courses.add(course);
		session.save(course);
		session.flush();
		course = new Course("Basic Science", subjects.get(2));
		courses.add(course);
		session.save(course);
		session.flush();
		course = new Course("Advanced Science", subjects.get(2));
		courses.add(course);
		session.save(course);
		session.flush();
		course = new Course("Basic Geography", subjects.get(3));
		courses.add(course);
		session.save(course);
		session.flush();
		course = new Course("Advanced Geography", subjects.get(3));
		courses.add(course);
		session.save(course);
		session.flush();
	}

	public void generateExams(){
		ArrayList<Question> list = new ArrayList();
		list.add(questions.get(0));	//exam for Basic Math
		list.add(questions.get(1));
		list.add(questions.get(3));
		list.add(questions.get(4));
		Exam exam = new Exam("24",list,90,"","",teachersList.get(0),courses.get(0));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(0));	//exam for Basic Math
		list.add(questions.get(2));
		list.add(questions.get(4));
		exam = new Exam("93",list,90,"","",teachersList.get(0),courses.get(0));
		exam.setPoints(exam.getQuestions().get(0),40 );
		exam.setPoints(exam.getQuestions().get(1),40 );
		exam.setPoints(exam.getQuestions().get(2),20 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(6));	//exam for Advanced Math
		list.add(questions.get(5));
		list.add(questions.get(7));
		exam = new Exam("68",list,60,"","",teachersList.get(1),courses.get(1));
		exam.setPoints(exam.getQuestions().get(0),30 );
		exam.setPoints(exam.getQuestions().get(1),40 );
		exam.setPoints(exam.getQuestions().get(2),30 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(8));	//exam for Basic English
		list.add(questions.get(9));
		list.add(questions.get(10));
		list.add(questions.get(11));
		exam = new Exam("84",list,75,"","",teachersList.get(2),courses.get(2));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(13));	//exam for Advanced English
		list.add(questions.get(14));
		list.add(questions.get(15));
		list.add(questions.get(16));
		exam = new Exam("52",list,60,"","",teachersList.get(2),courses.get(3));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(17));	//exam for Basic Science
		list.add(questions.get(18));
		list.add(questions.get(19));
		list.add(questions.get(20));
		exam = new Exam("73",list,60,"","",teachersList.get(3),courses.get(4));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(17));	//exam for Basic Science
		list.add(questions.get(18));
		list.add(questions.get(21));
		list.add(questions.get(22));
		exam = new Exam("64",list,70,"","",teachersList.get(4),courses.get(4));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(22));	//exam for Advanced Science
		list.add(questions.get(23));
		list.add(questions.get(24));
		list.add(questions.get(25));
		exam = new Exam("51",list,70,"","",teachersList.get(4),courses.get(5));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(27));	//exam for Basic Geography
		list.add(questions.get(29));
		list.add(questions.get(30));
		list.add(questions.get(31));
		exam = new Exam("03",list,80,"","",teachersList.get(5),courses.get(6));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(28));	//exam for Basic Geography
		list.add(questions.get(29));
		list.add(questions.get(31));
		list.add(questions.get(32));
		exam = new Exam("37",list,80,"","",teachersList.get(5),courses.get(6));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(32));	//exam for Advanced Geography
		list.add(questions.get(33));
		list.add(questions.get(34));
		list.add(questions.get(36));
		exam = new Exam("81",list,60,"","",teachersList.get(6),courses.get(7));
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
	}

	public void generateSolutions(){
		SolvedExam solvedExam = new SolvedExam("16.7.23", 90, exams.get(0));
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		ArrayList<Integer> list = new ArrayList();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		StudentData s1= new StudentData(studentsList.get(0),90,0,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		StudentData s2= new StudentData(studentsList.get(1),90,0,list, solvedExam);
		studentDataList.add(s2);
		session.save(s2);
		session.flush();
		solvedExam.calculateGrades();
	}
	public void connectToData() {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			generateSubjects();
			generateCourses();
			generateStudents();
			generateStaff();
			generateQuestions();
			generateExams();
			generateSolutions();
			session.getTransaction().commit();
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
	Exam exam = new Exam();
	Teacher currentTeacher = new Teacher();
	//currentTeacher.copy(teachersList.get(0)); // for example

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message = (Message)msg;
		String request = message.getMessage();
		try {
			if (request.isBlank()) {
				message.setMessage("Error! we got an empty message");
				client.sendToClient(message);
			} else {
				if(request.equals("get list of subjects for add question")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<String> subjectList = new ArrayList(subjects.size());
					for(int i=0; i<subjects.size(); i++)
						subjectList.add(i, subjects.get(i).getName());
					client.sendToClient(new Message("subjects list is ready for add question", subjectList));
					session.close();
				}
				else if(request.equals("get list of subjects for build exam")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<String> subjectList = new ArrayList(subjects.size());
					for(int i=0; i<subjects.size(); i++)
						subjectList.add(i, subjects.get(i).getName());
					client.sendToClient(new Message("subjects list is ready for build exam", subjectList));
					session.close();
				}
				else if(request.equals("get list of courses for add question")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					String subject = (String)message.getObject();
					ArrayList<String> courseList = new ArrayList();
					int count = 0;
					for(int i=0; i<courses.size(); i++) {
						if (courses.get(i).getSubject().getName().equals(subject)) {
							courseList.add(count, courses.get(i).getName());
							count++;
						}
					}
					System.out.println("courses list is ready");
					client.sendToClient(new Message("courses list is ready for add question", courseList));
					session.close();
				}
				else if(request.equals("get list of courses for build exam")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					String subject = (String)message.getObject();
					ArrayList<String> courseList = new ArrayList();
					int count = 0;
					for(int i=0; i<courses.size(); i++) {
						if (courses.get(i).getSubject().getName().equals(subject)) {
							courseList.add(count, courses.get(i).getName());
							count++;
						}
					}
					System.out.println("courses list is ready");
					client.sendToClient(new Message("courses list is ready for build exam", courseList));
					session.close();
				}
				else if(request.equals("new question")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					question.copy((Question)message.getObject());
					questions.add(question);
					session.save(question);
					session.flush();
					client.sendToClient(new Message("question added successfully",(Object)null));
					session.close();
				}
				else if(request.equals("new exam")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					exam.copy((Exam)message.getObject());
					exams.add(exam);
					session.save(exam);
					System.out.println("here4");
					session.flush();
					System.out.println("here5");
					client.sendToClient(new Message("exam added successfully",(Object)null));
					System.out.println("here6");
					session.close();
				}
				else if(request.equals("get subject for add question")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String)message.getObject();
					for(int i=0; i<subjects.size(); i++){
						if(subjects.get(i).getName().equals(name)){
							Subject chosenSubject= subjects.get(i);
							client.sendToClient(new Message("found subject for add question", chosenSubject));
							session.close();
							break;
						}
					}
				}
				else if(request.equals("get subject for build exam")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String)message.getObject();
					for(int i=0; i<subjects.size(); i++){
						if(subjects.get(i).getName().equals(name)){
							Subject chosenSubject= subjects.get(i);
							client.sendToClient(new Message("found subject for build exam", chosenSubject));
							session.close();
							break;
						}
					}
				}
				else if(request.equals("get course for add question")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String)message.getObject();
					for(int i=0; i<courses.size(); i++){
						if(courses.get(i).getName().equals(name)){
							Course chosenCourse= courses.get(i);
							client.sendToClient(new Message("found course for add question", chosenCourse));
							session.close();
							break;
						}
					}
				}
				else if(request.equals("get course for build exam")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String)message.getObject();
					for(int i=0; i<courses.size(); i++){
						if(courses.get(i).getName().equals(name)){
							Course chosenCourse= courses.get(i);
							client.sendToClient(new Message("found course for build exam", chosenCourse));
							session.close();
							break;
						}
					}
				}
				else if(request.equals("get question")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String)message.getObject();
					for(int i=0; i<questions.size(); i++){
						if(questions.get(i).getText().equals(name)){
							Question chosenQuestion = questions.get(i);
							client.sendToClient(new Message("found question", chosenQuestion));
							session.close();
							break;
						}
					}
				}
				else if(request.equals("get teacher")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					client.sendToClient(new Message("found teacher", currentTeacher));
					session.close();
				}
				else if(request.equals("get teacher for build exam")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					client.sendToClient(new Message("found teacher for build exam", currentTeacher));
					session.close();
				}
				else if(request.equals("get exam")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String)message.getObject();
					for(int i=0; i<exams.size(); i++){
						if(exams.get(i).getId_exam().equals(name)) {
							Exam chosenExam = exams.get(i);
							client.sendToClient(new Message("found exam", chosenExam));
							session.close();
							break;
						}
					}
				}
				else if(request.equals("update grade")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					StudentData s = (StudentData) message.getObject();
					int newGrade = s.getGrade();
					for(int i =0; i<studentDataList.size(); i++)
						if(studentDataList.get(i).getId() == s.getId()){
							studentDataList.get(i).setGrade(newGrade);
							session.update(studentDataList.get(i));
							session.getTransaction().commit();
							break;
						}
					client.sendToClient(new Message("grade updated successfully"));
				}
				session.flush();
				session.close();
			}
		} catch (IOException var13) {
			var13.printStackTrace();
		}

	}
}