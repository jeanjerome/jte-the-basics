void call(){

  podTemplate {
    node(POD_LABEL) {
      stage('checkout') {
        deleteDir()
        def checkout = checkout(scm)
      }
    } 
  }

}