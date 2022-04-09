// string(defaultValue: "TEST", description: 'What environment?', name: 'userFlag')
// choice(choices: ['TESTING', 'STAGING', 'PRODUCTION'], description: 'Select field for target environment', name: 'DEPLOY_ENV')
properties([
    parameters([
        string(defaultValue: 'TEST', name: 'userFlag', trim: true),
        choice(choices: ['TESTING', 'STAGING', 'PRODUCTION'], name: 'DEPLOY_ENV'),
        string(defaultValue:'1', name: 'count', trim: true)
        ])
    ])

pipeline {			
    agent any			
    stages {
        stage ("build") {		
            steps {
                script {
                    def  userFlag1= params.userFlag.split(',')
                    userFlag1.each {
                    for (int i = 0; i < params.count.toInteger()  ; i++){
                    build job: 'runjob', 
                    parameters: [
                        string(name: 'BRANCH', value: '$userFlag1' ),
                        string(name: 'DEPLOY_ENV', value: params.DEPLOY_ENV)
                       // [$class: 'ChoiceParameterValue', name: 'DEPLOY_ENV', value: '$DEPLOY_ENV']
                    ]}
                    }
                }
            }
        }
    }
}
