# **Gauge Framework'ü ile Web Otomasyon Testi Örneği** **-** **Linkedin**
# Linkedin Background
1. [x] **Login bilgileri girilir.**

# Linkedin AllSteps Web Scenario
1. [x] **Hesabın profili görüntülenir.**
2. [x] **Profile hakkında bölümü eklenir.**
3. [x] **Profile eğitim bilgileri eklenir.**
4. [x] **Profile pozisyon/iş bilgileri eklenir.**
5. [x] **Profile yetenek bilgileri eklenir.**
6. [x] **Profile sertifika bilgileri eklenir.**
7. [x] **Açık olan sayfa yenilenir.**
8. [x] **Profile eklenen bilgiler görüntülenir/incelenir.**
9. [x] **Sertifika görüntülemek için BTK Akademi sayfasına yeni sekmede gidilir.**
10. [x] **Yeni sekmede Linkedin profil sayfasına geri dönülür.**
11. [x] **Hesaptan çıkış yapılır.**

 # Getting Started (Gereksinimler)
 Gauge requires Java 8 (at least version 1.8.0_112 or greater) (Java jdk 1.8 sürümü ve üzeri.)
 https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

 **Intellij**
 https://www.jetbrains.com/idea/download/#section=windows (Maven, Gradle or Eclipse to be installed.)

 **Gauge Plugin**
 (gauge for java)

 # Running in IntelliJ
 If you are using the free Community Edition, you can easily install the "Gauge for Java" plugins.

 # Maven Dependency
 'gauge' should be add as a dependency on pom.xml so it will be installed automatically.
 ```
  <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
        <gauge.version>0.9.1</gauge.version>
        <selenium.version>3.141.59</selenium.version>
        <gauge.plugin.version>1.3.4</gauge.plugin.version>
        <maven.compiler.version>3.7.0</maven.compiler.version>
        <log4j.version>1.7.30</log4j.version>
        <gson.version>2.8.8</gson.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.thoughtworks.gauge</groupId>
            <artifactId>gauge-java</artifactId>
            <version>${gauge.version}</version>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>com.google.guava</groupId>
                    <artifactId>guava</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.1.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.21</version>
        </dependency>
        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <version>8.2.2.jre11</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>${gson.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.3</version>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <testResources>
            <testResource>
                <directory>src/test/resources</directory>
            </testResource>
        </testResources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.version}</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>com.thoughtworks.gauge.maven</groupId>
                <artifactId>gauge-maven-plugin</artifactId>
                <version>${gauge.plugin.version}</version>
            </plugin>
        </plugins>
    </build>





 ```
