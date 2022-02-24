void call(){

  podTemplate {
    node('kubeagent') {
      stage('checkout') {
        deleteDir()
        def checkout = checkout(scm)
      }
    } 
  }

}