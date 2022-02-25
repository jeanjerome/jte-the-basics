void call(){

  def label = "${UUID.randomUUID().toString()}-agent"
  podTemplate(
      cloud: 'kubernetes-cloud', 
      inheritFrom: 'default-agent',
      label: 'basic-agent', 
      name: 'basic-agent', 
      namespace: 'jenkins', 
      serviceAccount: 'jenkins-admin',
      containers: [
        containerTemplate(
          args: '', 
          command: '', 
          image: 'jenkins/inbound-agent:latest', 
          name: 'jnlp-agent', 
          workingDir: '/home/jenkins/agent'
        )
      ], 
    ) {

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