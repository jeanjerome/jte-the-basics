void call(){

  def label = "${UUID.randomUUID().toString()}-agent"
  podTemplate(
      cloud: 'kubernetes-cloud', 
      inheritFrom: 'default-agent',
      label: 'basic-agent', 
      name: 'basic-agent', 
      namespace: 'jenkins', 
      serviceAccount: 'jenkins-admin'
    ) {

    node(label) {
      container('basic-agent') {
        stage('checkout') {
          deleteDir()
          def checkout = checkout(scm)
        }
      }
    }
  }
  
}