void call(){

    podTemplate(
      cloud: 'kubernetes-cloud', 
      inheritFrom: 'default-agent', 
      name: 'maven-agent', 
      namespace: 'jenkins', 
      serviceAccount: 'jenkins-admin',
      volumes: [hostPathVolume(hostPath: '/Users/jeanjerome/PROJETS/jenkins-template-engine/jenkins-workspaces', mountPath: '/home/jenkins/agent')],
      containers: [
        containerTemplate(
          args: '', 
          command: 'mvn clean install', 
          image: 'maven:openjdk', 
          name: 'maven'
        )
      ], 
    )
    
    {
      node(POD_LABEL) {
        container('maven-agent') {
          stage('maven build') {
            sh '''
            mvn -v
            '''
          }
        }
      }
    }
    
}

