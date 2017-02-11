# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/xenial64"

  # forward development web server port 9000
  config.vm.network "forwarded_port", guest: 9000, host: 9000

  # share mobtown project directory using rsync because VirtualBox shared 
  # directories do not play nicely with npm
  config.vm.synced_folder ".", "/vagrant", type: "rsync", rsync__exclude: ".git/"

  config.vm.provider "virtualbox" do |vb|
    # need a lot of memory for npm install via gradle
    vb.memory = "2048"
  end

  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update
    sudo apt-get install -y default-jdk zip
    sudo apt-get -y --no-install-recommends install curl apt-transport-https ca-certificates software-properties-common
    curl -fsSL https://apt.dockerproject.org/gpg | sudo apt-key add -
    sudo add-apt-repository "deb https://apt.dockerproject.org/repo/ ubuntu-$(lsb_release -cs) main"
    sudo apt-get update
    sudo apt-get -y install docker-engine
    sudo -i curl -sL "https://github.com/docker/compose/releases/download/1.10.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo -i chmod +x /usr/local/bin/docker-compose
    sudo gpasswd -a ubuntu docker
    sudo systemctl restart docker
  SHELL
end
