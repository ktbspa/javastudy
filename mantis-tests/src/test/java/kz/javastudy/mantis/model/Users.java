package kz.javastudy.mantis.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.ForwardingSet;

public class Users extends ForwardingSet<UserData> {
   private Set<UserData> delegate;
   public Users(Collection<UserData> users) { this.delegate = new HashSet<>(users); }

   @Override
   protected Set<UserData> delegate() {
      return delegate;
   }
}