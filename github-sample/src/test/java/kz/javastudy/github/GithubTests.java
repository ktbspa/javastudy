package kz.javastudy.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

   @Test
   public void testCommits() throws IOException {
      Github github = new RtGithub("0aae807b9f17cd30c3ad469607c182b50436b654");
      RepoCommits commits = github.repos().get(new Coordinates.Simple("ktbspa", "javastudy")).commits();
      for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
         System.out.println(new RepoCommit.Smart(commit).message());
      }
   }
}