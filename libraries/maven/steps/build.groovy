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
          command: '', 
          image: 'jenkins/inbound-agent:latest', 
          name: 'jnlp', 
          workingDir: '/home/jenkins/agent'
        ),
        containerTemplate(
          args: '', 
          command: '', 
          image: 'maven:openjdk', 
          name: 'maven', 
          workingDir: '/home/jenkins/agent'
        )
      ], 
    )
    
    {
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

