void call(){
    
    podTemplate(containers: [
        containerTemplate(
            name: 'maven', 
            image: 'maven:latest', 
            command: 'sleep', 
            args: '30d'
            )
      ]) {

      node(POD_LABEL) {
          container('maven') {
            stage('maven build') {
              sh '''
              mvn clean install
              '''
            }
          }

      }
    }
    
}

