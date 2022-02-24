void call(){

  podTemplate {
    node('kube-agent') {
      stage('checkout') {
        deleteDir()
        def checkout = checkout(scm)
      }
    } 
  }

}