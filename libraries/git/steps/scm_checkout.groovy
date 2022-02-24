void call(){

  kubernetes.pod('buildpod').withImage('maven').inside {
    deleteDir()
    def checkout = checkout(scm)
  }
}
