Создать файл ${user.home}/.m2/settings.xml в который добавить следующий код:

<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd" 
    xmlns="http://maven.apache.org/SETTINGS/1.1.0" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <servers>
        <server>
            <id>gitlab-maven</id>
            <configuration>
                <httpHeaders>
                    <property>
                        <name>Private-Token</name>
                        <value>yywYAPannroKRhDtJDfa</value>
                    </property>
                </httpHeaders>
            </configuration>
        </server>
    </servers>
</settings>
