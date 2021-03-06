void call(){

    podTemplate(
      cloud: 'kubernetes-cloud', 
      inheritFrom: 'default-agent',
      name: 'basic-agent', 
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
        )
      ], 
    ) 
    
    {
      node(POD_LABEL) {
        container('basic-agent') {
          stage('checkout') {
            deleteDir()
            def checkout = checkout(scm)
          }
        }
      }
    }
  
}