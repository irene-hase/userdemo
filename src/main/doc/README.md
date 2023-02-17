# Berücksichtigte Punkte

Layering: die Demo Applikation ist in 3 Layers geschnitten

* ## Presentation Layer

  * ist unterteilt nach MVC

    * **View** == die *.xhtml, *.js, *.css Dateien
    * **Model** == *UserDto.java* Abstaction der Daten von Entity User.java, mit Daten die für die UI von Relevanz sind
    * **Contoller** == die Page Beans, z.B, LoginPageBean.java
  * das Model, Contoller müssen *implements Serializable*, weil die je nach Scope serialisiert und deseralisiert werden müssen
  * Es gib einen *PasswordValidator.java* welches *org.omnifaces.validator.MultiFieldValidator* implementiert und schaut on beide angetragene Passwörter übereinstimmen und ob das gewählte Passwort der gewünschten Policy entspricht. Hier ist die Policy fix könnte aber auch geladen werden. Man könnte auch hier ein **Strategy-Pattern** einsetzen, das nach Belieben ausgetauscht werden könnte.
* ## Business Logic Layer

  * enthält die User Verifizierung, VerificationStatus Verwaltung. *UserServiceImpl.java* und *FormBasedAuthenticationService.java*. Das Hochzählen fehlgeschalgene Logins, das Ändern des VerificationStatus
  * Users with VerificationStatus.BLOCKED OR VerificationStatus.UNVERIFIED müssen durch den Admistrator auf VerificationStatus.VERIFIED gesetz werden.
* ## Data Access Layer

  * der CRUD-Service *UserRepository.java* für die Entities *User.java*
  * *User.java* ist umfangreich mit alle Daten für diese Appilikation, wobei UserDto.java nur die Ausschnitt/Teilmenge davon beinhaltet, nur das was von Relevanz für die UI ist.
  * *VerificationStatus,java* ist ein enum representiert Stati den Users annehmen kann und *UserRole.java* ist ein enum für User Kategorien.
  * Das Passwort sollte nicht plain in DB abgespeichert werden hierfür gibt es eine Helper AuthenticationHelper.java für encode Pwd.

Eingehaltene Prinzipien

### SOLID-Principles

**S**ingle Responsibility Principle:

* es gibt den *UserRepository.java* für DAO Zugriff und *AuthenticationService.java* für die Authentifizierung. Jeder Service hat seine Zuständigkeit.

**O**pen Close Principle

**L**iskos Substitution Principle

**I**nterface Segregation Principle

**D**epenedency Inversion Principle, Implementierung erfolgt gegen Interfaces nicht konkrette Klassen

### Es gibt noch Einiges zu tun

* Anpassen der Hash & Equals Mehoden
* Fixen der JPA für die In-Memory Datenbank von z.B. WildFly
* Aufsplitten in Maven Multi Module project
* Implementierung ausreichender Testabdeckung (JUnit5, AssertJ &Co)
* Vollständiges Exception-Handling
* Logging gemäß der DSGVO und festgelegten Richtlinien was geloggt werden soll und wie (TRACE,DEBUG,INFO)
* Hot-Deployment für ein schnelles RoundTrip während er UI Entwicklung
