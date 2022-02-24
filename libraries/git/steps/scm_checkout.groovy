void call(){

  podTemplate(containers: [
    containerTemplate(
        name: 'jnlp', 
        image: 'jenkins/inbound-agent:latest'
        )
  ]) {

    node(POD_LABEL) {
      container('jnlp') {
        stage('checkout') {
          deleteDir()
          def checkout = checkout(scm)
        }
      }
    }
    
  }
}