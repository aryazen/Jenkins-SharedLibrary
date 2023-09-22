def copyfile(){
  echo "Coping Application files"
  sh '''
    echo "Copying Files to ${environ} Server"
  '''
  if (env.environ == "dev"){
      sh '''
        rsync -avzh ${WORKSPACE}/* --exclude 'Jenkinsfile' --exclude '.git' ${userid}@${server}:${deployPath}${AppName}
      '''
  }else if(env.environ == "prod")
  {
      sh '''
        rsync -avh -e "ssh -p 65002" ${WORKSPACE}/* --exclude 'Jenkinsfile' --exclude '.git' ${userid}@${server}:${deployPath}
      '''      
  }
}
