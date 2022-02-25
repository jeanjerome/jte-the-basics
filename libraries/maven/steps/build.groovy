void call(){

    podTemplate(
      cloud: 'kubernetes-cloud', 
      containers: [
        containerTemplate(
          args: '', 
          command: '', 
          image: 'maven:latest', 
          name: 'maven-agent', 
          workingDir: '/home/jenkins/agent'
        )
      ], 
      inheritFrom: 'default-agent', 
      label: 'maven-agent', 
      name: 'maven-agent', 
      namespace: 'jenkins', 
      serviceAccount: 'jenkins-admin'
    ) {

      node(POD_LABEL) {
          container('maven-agent') {
            stage('maven build') {
              sh '''
              mvn clean install
              '''
            }
          }

      }
    }
    
}

