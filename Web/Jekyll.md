

# install Jekyll

[toc]

---

## Install Command Line Tools

## Install Ruby

## Install Jekyll


```java
gem install --user-install bundler jekyll

ruby -v

echo 'export PATH="$HOME/.gem/ruby/x.x.0/bin:$PATH"' >> ~/.bash_profile
// replacing the X.X with the first two digits of your Ruby version.
// Every time update Ruby to a version with a different first two digits, you will need to update your path to match.
```

## Global InstallPermalink

```
sudo gem install bundler
sudo gem install -n /usr/local/bin/ jekyll
```


---

## Prerequisites

```java
gem install jekyll bundler

// To create a new Gemfile to list your project’s dependencies run:
bundle init

// Now edit the Gemfile and add jekyll as a dependency:
gem "jekyll"
```

---

## Create a site

```
jekyll new ./Documents/ochohome
cd ./Documents/ochohome

$ bundle exec jekyll serve
```


---

## github 

```
$ git init
$ git add -A .
$ git commit -m "8.25"
$ git push -u origin master
```

---


1. `_config.yml` updated, webpages does not refresh.
2. Git push: email `nuild-n-test`. no error = success


---


.
