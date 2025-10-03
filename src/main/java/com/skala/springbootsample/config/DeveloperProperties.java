package com.skala.springbootsample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperties {

    private Owner owner = new Owner();
    private Team team = new Team();

    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public static class Owner {
        private String name;
        private String role;
        private String level;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getLevel() { return level; }
        public void setLevel(String level) { this.level = level; }
    }

    public static class Team {
        private String position;
        private String detail;

        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }

        public String getDetail() { return detail; }
        public void setDetail(String detail) { this.detail = detail; }
    }
}

