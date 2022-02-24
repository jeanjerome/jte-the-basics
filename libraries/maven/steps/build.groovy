void call(){
    
    podTemplate(containers: [
        containerTemplate(
            name: 'maven', 
            image: 'maven:latest'
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

