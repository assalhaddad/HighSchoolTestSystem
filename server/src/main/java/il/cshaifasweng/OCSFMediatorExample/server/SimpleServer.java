package il.cshaifasweng.OCSFMediatorExample.server;

import com.mysql.cj.protocol.x.XMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ChatServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		configuration.addAnnotatedClass(Request.class);
		configuration.addAnnotatedClass(LoginInfo.class);


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
	ArrayList<Request> requests = new ArrayList();
	ArrayList<LoginInfo> loginInfo = new ArrayList();
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

	public void generateStaff(){
		ArrayList<Subject> list = new ArrayList();
		list.add(subjects.get(0)); // Math
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
		list.add(subjects.get(1)); //Math + English
		teacher = new Teacher("Or Meir","orMeir","or88");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		list.clear();
		list.add(subjects.get(2)); //Science
		teacher = new Teacher("Moran Feldman","moranFeldman","moran2");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		list.add(subjects.get(3)); //Science + Geography
		teacher = new Teacher("Norit Cohen","noritCohen","norit77");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		list.clear();
		list.add(subjects.get(3)); //Geography
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
		list.add(subjects.get(0));
		list.add(subjects.get(2)); //Geography + Math + Science
		teacher = new Teacher("Merav Michaeli","meravMichaeli","merav46");
		teacher.setSubjects(list);
		teachersList.add(teacher);
		session.save(teacher);
		session.flush();
		principal = new Principal("Malki Grosman","malkiGrosman","thePrincipal1");
		session.save(principal);
		session.flush();
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
		question = new Question("13702","Wne ____ the dog?","are","it","is","does",3,subjects.get(1),list);
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
		Exam exam = new Exam("24",list,3,"","",teachersList.get(0),courses.get(0),"a1cd");
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
		list.add(questions.get(0));	//exam for Basic Math
		list.add(questions.get(2));
		list.add(questions.get(4));
		exam = new Exam("93",list,2,"","",teachersList.get(0),courses.get(0),"1234");
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
		exam = new Exam("68",list,3,"","",teachersList.get(1),courses.get(1),"1311");
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
		exam = new Exam("84",list,1,"","",teachersList.get(2),courses.get(2),"A1A2");
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
		exam = new Exam("52",list,2,"","",teachersList.get(2),courses.get(3),"7897");
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
		exam = new Exam("73",list,1,"","",teachersList.get(3),courses.get(4),"9999");
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
		exam = new Exam("64",list,1,"","",teachersList.get(4),courses.get(4),"6789");
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
		exam = new Exam("51",list,2,"","",teachersList.get(4),courses.get(5),"1555");
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
		exam = new Exam("03",list,2,"","",teachersList.get(5),courses.get(6),"1964");
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
		exam = new Exam("38",list,3,"","",teachersList.get(5),courses.get(6),"1965");
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();

		//for aseel:
		list.add(questions.get(28));	//exam random
		list.add(questions.get(29));
		list.add(questions.get(31));
		list.add(questions.get(32));
		exam = new Exam("37",list,2,"","",teachersList.get(5),courses.get(6),"2580");
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
		exam = new Exam("81",list,1,"","",teachersList.get(6),courses.get(7),"2001");
		for(int i=0; i<4; i++)
			exam.setPoints(exam.getQuestions().get(i),25 );
		exams.add(exam);
		session.save(exam);
		session.flush();
		list.clear();
	}

	public void generateSolutions(){
		SolvedExam solvedExam = new SolvedExam( exams.get(0).getTime(), exams.get(0));  // exam1
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		ArrayList<Integer> list = new ArrayList();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		StudentData s1= new StudentData(studentsList.get(0),"16.07.23 16:02:30",1.5,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(1),"11.07.23 16:43:20",2.35,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(2),"21.06.23 10:07:20",2.9,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(3),"30.06.23 13:07:30",1.86,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(12),"27.07.23 10:08:40",1.37,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam(exams.get(1).getTime(), exams.get(1)); //exam2
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(1),"03.08.23 13:06:40",1.2,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		s1= new StudentData(studentsList.get(4),"07.07.23 16:26:43",1.7,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(2);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(6),"08.07.23 16:10:43",0.35,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(4);
		s1= new StudentData(studentsList.get(7),"01.07.23 16:20:43",1.38,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(10),"19.06.23 09:20:43",1.29,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam(exams.get(2).getTime(), exams.get(2)); //exam3
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(3),"17.06.23 09:20:43",2.5,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		s1= new StudentData(studentsList.get(2),"10.06.23 09:20:43",2.52,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(2);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(7),"13.06.23 09:20:43",2.1,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(4);
		s1= new StudentData(studentsList.get(8),"11.06.23 09:20:43",2.542,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(11),"15.06.23 09:20:43",2.586,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam( exams.get(3).getTime(), exams.get(3));  // exam4
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(0),"28.06.23 09:20:43",0.35,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(3),"24.06.23 09:20:43",0.37,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(6),"25.06.23 09:20:43",0.39,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(8),"26.06.23 09:20:43",0.291,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(12),"27.06.23 09:20:43",0.97,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam( exams.get(4).getTime(), exams.get(4));  // exam5
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(0),"29.06.23 09:20:43",1.46,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(10),"30.06.23 09:20:43",1.486,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(11),"01.06.23 09:20:43",1.92,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(12),"02.06.23 09:20:43",1.67,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(15),"03.06.23 09:20:43",0.39,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam( exams.get(5).getTime(), exams.get(5));  // exam6
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(2),"04.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(5),"05.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(13),"06.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(14),"07.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(15),"08.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam(exams.get(6).getTime(), exams.get(6));  // exam7
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(0),"09.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(1),"10.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(6),"01.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(13),"02.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(15),"03.06.23 09:20:43",1,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam(exams.get(7).getTime(), exams.get(7));  // exam8
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(1),"04.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(5),"05.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(7),"06.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(8),"07.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(9),"08.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam(exams.get(8).getTime(), exams.get(8));  // exam9
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(0),"09.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(5),"10.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(10),"11.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(14),"12.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(9),"13.06.23 09:20:43",1.32,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam( exams.get(9).getTime(), exams.get(9));  // exam10
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(2),"14.06.23 09:20:43",2.78,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(7),"15.06.23 09:20:43",2.21,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(10),"16.06.23 09:20:43",2.3682,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(13),"17.06.23 09:20:43",2.63,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(9),"18.06.23 09:20:43",2.212,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
		solvedExam = new SolvedExam( exams.get(10).getTime(), exams.get(10));  // exam11
		solvedExamList.add(solvedExam);
		session.save(solvedExam);
		session.flush();
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(3);
		s1= new StudentData(studentsList.get(0),"01.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		s1= new StudentData(studentsList.get(4),"02.06.23 09:20:43",2,true,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(12),"03.06.23 09:20:43",1.34,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(8),"04.06.23 09:20:43",1.254,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(3);
		s1= new StudentData(studentsList.get(14),"05.06.23 09:20:43",1.43,false,list, solvedExam);
		studentDataList.add(s1);
		session.save(s1);
		session.flush();
		list.clear();
		solvedExam.calculateGrades();
	}

	public void generateLoginInfo(){
		for(int i = 0; i<studentsList.size(); i++){
			LoginInfo newInfo =new LoginInfo(studentsList.get(i).getUsername(),studentsList.get(i).getPassword(),"student");
			loginInfo.add(newInfo);
			session.save(newInfo);
			session.flush();
		}
		for(int i = 0; i<teachersList.size();i++){
			LoginInfo newInfo =new LoginInfo(teachersList.get(i).getUsername(),teachersList.get(i).getPassword(),"teacher");
			loginInfo.add(newInfo);
			session.save(newInfo);
			session.flush();
		}
		LoginInfo newInfo =new LoginInfo("malkiGrosman","thePrincipal1","principal");
		loginInfo.add(newInfo);
		session.save(newInfo);
		session.flush();
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
			generateLoginInfo();
			session.getTransaction().commit();
		} catch (Exception var5) {
			if (session != null) {
				session.getTransaction().rollback();
			}

			System.err.println("An error occurred, changes have been rolled back.");
			var5.printStackTrace();
		} finally {
			session.close();
			session.getSessionFactory().close();
		}

	}
	Question question = new Question();
	Exam exam = new Exam();
	Teacher currentTeacher = new Teacher();
	Request requestExtraTime = new Request();
	StudentData studentData=new StudentData();
	SolvedExam solvedExam=new SolvedExam();
	ChatServer clients=new ChatServer(3000) {
		@Override
		protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		}
	};


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
					currentTeacher.copy((Teacher)message.getObject());
					ArrayList<String> subjectList = new ArrayList();
					for(int i=0; i<currentTeacher.getSubjects().size(); i++)
						subjectList.add(i, currentTeacher.getSubjects().get(i).getName());
					session.close();
					client.sendToClient(new Message("subjects list is ready for add question", subjectList));
				}
				else if(request.equals("get list of subjects for build exam")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<String> subjectList = new ArrayList();
					currentTeacher.copy((Teacher)message.getObject());
					for(int i=0; i<currentTeacher.getSubjects().size(); i++)
						subjectList.add(i, currentTeacher.getSubjects().get(i).getName());
					session.close();
					client.sendToClient(new Message("subjects list is ready for build exam", subjectList));
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
					//System.out.println("courses list is ready");
					session.close();
					client.sendToClient(new Message("courses list is ready for add question", courseList));
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
					//System.out.println("courses list is ready");
					session.close();
					client.sendToClient(new Message("courses list is ready for build exam", courseList));
				}
				else if(request.equals("new question")){
					Question temp = new Question();
					session=sessionFactory.openSession();
					session.beginTransaction();
					question.copy((Question)message.getObject());
					temp.copy(question);
					for(int i=0; i<subjects.size(); i++)
						if(subjects.get(i).getName().equals(question.getSubject().getName())) {
							subjects.get(i).getQuestions().add(temp);
							session.update(subjects.get(i));
						}
					for(int i=0; i<courses.size(); i++){
						for(int j=0; j<question.getCourses().size(); j++)
							if(courses.get(i).getName().equals(question.getCourses().get(j).getName())) {
								courses.get(i).getQuestions().add(temp);
								session.update(courses.get(i));
							}
					}
					questions.add(temp);
					session.save(temp);
					session.flush();
					session.getTransaction().commit();
					session.close();
					client.sendToClient(new Message("question added successfully",(Object)null));
				}
				else if(request.equals("new studentData 2.0")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					studentData.copy((StudentData)message.getObject());
					StudentData temp = new StudentData();
					temp.copy(studentData);
					for(int i=0; i<solvedExamList.size(); i++){
						if(solvedExamList.get(i).getId() == temp.getSolvedExam().getId())
							for(int k=0; k<solvedExamList.get(i).getData().size(); k++){
								if(solvedExamList.get(i).getData().get(k).getStudent().getId_student().equals(temp.getStudent().getId_student())){
									session.remove(solvedExamList.get(i).getData().get(k));
									//System.out.println("Removing "+solvedExamList.get(i).getData().get(k).getName()+" from solved exam list");
									solvedExamList.get(i).getData().remove(k);
									break;
								}
							}
					}
					for(int i=0; i<solvedExamList.size(); i++){
						if(solvedExamList.get(i).getId() == temp.getSolvedExam().getId()){
							solvedExamList.get(i).getData().add(temp);
							//System.out.println("adding "+temp.getName()+" to solved exam list");
							break;
						}
					}
					studentDataList.add(temp);
					session.save(temp);
					session.flush();
					session.getTransaction().commit();
					session.close();
					client.sendToClient(new Message("studentData added successfully 2.0",(Object)null));
				}
				else if(request.equals("new studentData")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					studentData.copy((StudentData)message.getObject());
					StudentData temp = new StudentData();
					temp.copy(studentData);
					for(int i=0; i<solvedExamList.size(); i++){
						if(solvedExamList.get(i).getId() == temp.getSolvedExam().getId())
							for(int k=0; k<solvedExamList.get(i).getData().size(); k++){
								if(solvedExamList.get(i).getData().get(k).getStudent().getId_student().equals(temp.getStudent().getId_student())){
									session.remove(solvedExamList.get(i).getData().get(k));
									//System.out.println("Removing "+solvedExamList.get(i).getData().get(k).getName()+" from solved exam list");
									solvedExamList.get(i).getData().remove(k);
									break;
								}
							}
					}
					for(int i=0; i<solvedExamList.size(); i++){
						if(solvedExamList.get(i).getId() == temp.getSolvedExam().getId()){
							solvedExamList.get(i).getData().add(temp);
							//System.out.println("adding "+temp.getName()+" to solved exam list");
							break;
						}
					}
					studentDataList.add(temp);
					session.save(temp);
					session.flush();
					session.getTransaction().commit();
					session.close();
					client.sendToClient(new Message("studentData added successfully",(Object)null));
				}
				else if(request.equals("new solvedExam")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					solvedExam.copy((SolvedExam) message.getObject());
					solvedExamList.add(solvedExam);
					for(int i=0; i<exams.size(); i++)
						if(exams.get(i).getId_exam().equals(solvedExam.getExam().getId_exam())){
							exams.get(i).setSolvedExam(solvedExam);
							break;
						}
					session.save(solvedExam);
					session.flush();
					session.getTransaction().commit();
					session.close();
					client.sendToClient(new Message("solvedExam added successfully",solvedExam));
				}
				else if(request.equals("new exam")){
					Exam temp = new Exam();
					session=sessionFactory.openSession();
					session.beginTransaction();
					exam.copy((Exam) message.getObject());
					temp.copy(exam);

					for(int i=0; i<teachersList.size(); i++)
						if(teachersList.get(i).getName().equals(temp.getAuthor().getName()))
							teachersList.get(i).getExams().add(temp);

					for(int i=0; i<courses.size(); i++)
						if(courses.get(i).getName().equals(temp.getCourse().getName()))
							courses.get(i).getExams().add(temp);

					for(int i=0; i<teachersList.size(); i++)
						for(int j=0; j<teachersList.get(i).getSubjects().size(); j++)
							for(int h=0; h<teachersList.get(i).getSubjects().get(j).getCourses().size(); h++)
								for(int k=0; k<courses.size(); k++)
									if(teachersList.get(i).getSubjects().get(j).getCourses().get(h).getName().equals(courses.get(k).getName())) {
										teachersList.get(i).getSubjects().get(j).getCourses().remove(h);
										teachersList.get(i).getSubjects().get(j).getCourses().add(courses.get(k));
									}

					exams.add(temp);
					session.save(temp);
					session.flush();
					session.getTransaction().commit();
					session.close();
					client.sendToClient(new Message("exam added successfully",(Object)null));
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
				else if(request.equals("get exam")) {
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String) message.getObject();
					for (int i = 0; i < exams.size(); i++) {
						if (exams.get(i).getId_exam().equals(name)) {
							Exam chosenExam = exams.get(i);
							client.sendToClient(new Message("found exam", chosenExam));
							session.close();
							break;
						}
					}
				}
				else if(request.equals("check exam id")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					String name = (String)message.getObject();
					for(int i=0; i<exams.size(); i++){
						if(exams.get(i).getId_exam().equals(name)) {
							client.sendToClient(new Message("found duplicate exam id", null));
							session.close();
							return;
						}
					}
					client.sendToClient(new Message("didnt find duplicate exam id", null));
					session.close();
				}
				else if(request.equals("get list of students ids")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					String idStudent = (String)message.getObject();
					for(int i=0; i<studentsList.size(); i++)
						if(studentsList.get(i).getId_student().equals(idStudent))
						{
							Student chosenStudent = studentsList.get(i);
							client.sendToClient(new Message("found student", chosenStudent));
							session.close();
							return;
						}
					client.sendToClient(new Message("didn't find student", null));
					session.close();

				}
				else if(request.equals("get list of codes")){

					session = sessionFactory.openSession();
					session.beginTransaction();
					String examMessage = (String)message.getObject();
					for(int i=0; i<exams.size(); i++){
						if(exams.get(i).getCode4Digits().equals(examMessage)) {
							Exam chosenExam = exams.get(i);
							//System.out.println(chosenExam.getId_exam());
							client.sendToClient(new Message("found exam for do exam", chosenExam));
							session.close();
							return;
						}
					}
					client.sendToClient(new Message("didn't find exam", null));
					session.close();
				}

				else if(request.equals("get list of codes 2.0")){

					session = sessionFactory.openSession();
					session.beginTransaction();
					String examMessage = (String)message.getObject();
					for(int i=0; i<exams.size(); i++){
						if(exams.get(i).getCode4Digits().equals(examMessage)) {
							Exam chosenExam = exams.get(i);
							//System.out.println(chosenExam.getId_exam());
							client.sendToClient(new Message("found exam 2.0", chosenExam));
							session.close();
							return;
						}
					}
					client.sendToClient(new Message("didn't find exam 2.0", null));
					session.close();
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
							session.close();
							break;
						}
					client.sendToClient(new Message("grade updated successfully"));
				}
				else if(request.equals("get this student to login")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					Student current = new Student();
					LoginInfo currentLog = new LoginInfo();
					currentLog.copy((LoginInfo)message.getObject());
					for(int i=0; i<studentsList.size();i++){
						if(studentsList.get(i).getUsername().equals(currentLog.getUsername()) && studentsList.get(i).getPassword().equals(currentLog.getPassword()))
						{
							current.copy(studentsList.get(i));
							break;
						}
					}
					client.sendToClient(new Message("found the student to login", current));
					session.close();
				}
				else if(request.equals("get this teacher to login")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					Teacher current = new Teacher();
					LoginInfo currentLog = new LoginInfo();
					currentLog.copy((LoginInfo)message.getObject());
					for(int i=0; i<teachersList.size();i++){
						if(teachersList.get(i).getUsername().equals(currentLog.getUsername()) && teachersList.get(i).getPassword().equals(currentLog.getPassword()))
						{
							//currentTeacher.copy(teachersList.get(i)); // just added assal
							current.copy(teachersList.get(i));
							break;
						}
					}
					client.sendToClient(new Message("found the teacher to login", current));
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
					Request temp = new Request();
					temp.copy(requestExtraTime);
					requests.add(temp);
					session.save(temp);
					session.flush();
					client.sendToClient(new Message("request added successfully",(Object)null));
					session.close();
				}
				else if(request.equals("get list of requests")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<Request> requests1 = new ArrayList();
					int count =0;
					for(int i=0; i<requests.size(); i++){
						if(requests.get(i).getIsDone() == 0){
							requests1.add(count, requests.get(i));
							count++;
						}

					}
					client.sendToClient(new Message("requests list is ready", requests1));
					session.close();
				}
				else if(request.equals("get list of requests to update")){
					System.out.println("update");
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<Request> requests1 = new ArrayList();
					int count =0;
					for(int i=0; i<requests.size(); i++){
						if(requests.get(i).getIsDone() == 0){
							//System.out.println(i);
							requests1.add(count, requests.get(i));
							count++;
						}

					}
					session.close();
					client.sendToClient(new Message("requests list is ready to update", requests1));
				}
				else if(request.equals("approve this request")){
					System.out.println("approve");
					session=sessionFactory.openSession();
					session.beginTransaction();
					requestExtraTime.copy((Request)message.getObject());

					for(int j=0; j<requests.size(); j++){
						if(requests.get(j).getId() == requestExtraTime.getId())
						{
							requests.get(j).setIsDone(1);
						}

					}
					requestExtraTime.setIsDone(1);
					session.update(requestExtraTime);
					session.flush();
					//Exam temp = new Exam();
					for (int i = 0; i < exams.size(); i++) {
						if (exams.get(i).getId_exam().equals(requestExtraTime.getExamId())) {
							exams.get(i).getSolvedExam().setUpdatedTime(requestExtraTime.getMinutes()+ exams.get(i).getTime());
							System.out.println("after updating: "+exams.get(i).getSolvedExam().getUpdatedTime());
							for(int k=0; k<solvedExamList.size(); k++) {
								System.out.println("comparing between:");
								System.out.println(solvedExamList.get(k).getId());
								System.out.println(exams.get(i).getSolvedExam().getId());
								if (solvedExamList.get(k).getId() == exams.get(i).getSolvedExam().getId()) {
									solvedExamList.get(k).copy(exams.get(i).getSolvedExam());
									System.out.println("updated time: " + solvedExamList.get(k).getUpdatedTime());
									session.update(solvedExamList.get(k));
									session.flush();
									break;
								}
							}
							session.flush();
						}
					}
					String[] myArray = new String[2];
					myArray[0] = String.valueOf(requestExtraTime.getMinutes());
					myArray[1] = requestExtraTime.getExamId();
					sendToAllClients(new Message("request approved successfully", myArray)); // first node is the time, second is the exam's id
					session.close();
				}
				else if(request.equals("get questions for principal")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<Question> questions1 = new ArrayList(questions.size());
					for(int i=0; i<questions.size(); i++)
						questions1.add(i, questions.get(i));
					session.close();
					client.sendToClient(new Message("questions list is ready for principal", questions1));
				}
				else if(request.equals("get exams for grades principal")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<Exam> exams1 = new ArrayList(exams.size());
					for(int i=0; i<exams.size(); i++)
						exams1.add(i, exams.get(i));
					session.close();
					client.sendToClient(new Message("exams list is ready for grades principal", exams1));
				}
				else if(request.equals("get lists of usernames and passwords")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<LoginInfo> loginInfos = new ArrayList(loginInfo.size());
					for(int i=0; i<loginInfo.size(); i++)
						loginInfos.add(i, loginInfo.get(i));
					session.close();
					client.sendToClient(new Message("login list is ready", loginInfos));
				}
				else if(request.equals("get exams for exam page principal")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<Exam> exams1 = new ArrayList(exams.size());
					for(int i=0; i<exams.size(); i++)
						exams1.add(i, exams.get(i));
					session.close();
					client.sendToClient(new Message("exams list is ready for exam principal", exams1));
				}
				else if(request.equals("get exams for request extra time")){
					session=sessionFactory.openSession();
					session.beginTransaction();
					Teacher currentTeacher = (Teacher)message.getObject();
					for(int i=0; i<teachersList.size(); i++)
						if(teachersList.get(i).getName().equals(currentTeacher.getName())) {
							currentTeacher.copy(teachersList.get(i));
							break;
						}
					ArrayList<String> examsIdList = new ArrayList();
					int count = 0;
					Subject subject = new Subject();
					Course course = new Course();
					for(int i=0; i<currentTeacher.getSubjects().size(); i++){
						subject.copy(currentTeacher.getSubjects().get(i));
						for(int j=0; j<subject.getCourses().size(); j++){
							course.copy(subject.getCourses().get(j));
							for(int k=0; k<course.getExams().size(); k++){
								//System.out.println(course.getExams().get(k).getId_exam());
								examsIdList.add(count, course.getExams().get(k).getId_exam());
								count++;
							}
						}
					}
					session.close();
					client.sendToClient(new Message("exams list for request extra time is ready", examsIdList));
				}
				else if(request.equals("get updated time")){

					session=sessionFactory.openSession();
					session.beginTransaction();
					exam.copy((Exam) message.getObject());

					for (int i = 0; i < exams.size(); i++) {
						if (exams.get(i).getId_exam().equals(exam.getId_exam())) {
							client.sendToClient(new Message("updated time", exams.get(i).getSolvedExam().getUpdatedTime()));
							return;
						}
					}
				}
				else if(request.equals("get list of Questions Id")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					ArrayList<String> questionsIds = new ArrayList();
					for(int i=0; i<questions.size(); i++)
						questionsIds.add(i, questions.get(i).getId_question());
					session.close();
					client.sendToClient(new Message("list of Questions Id is ready", questionsIds));
				}
				else if(request.equals("decline this request")){
					session = sessionFactory.openSession();
					session.beginTransaction();
					requestExtraTime.copy((Request)message.getObject());

					for(int j=0; j<requests.size(); j++){
						if(requests.get(j).getId() == requestExtraTime.getId())
							requests.get(j).setIsDone(-1);
					}
					requestExtraTime.setIsDone(-1);
					session.update(requestExtraTime);
					session.flush();

					sendToAllClients(new Message("request declined successfully", -1));
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