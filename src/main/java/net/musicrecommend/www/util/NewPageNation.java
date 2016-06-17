package net.musicrecommend.www.util;

import java.io.Serializable;

public class NewPageNation implements Serializable {

   private long pg; // 현재 페이지
   private long page_size; // 페이지 당 출력할 게시물 수
   private long block_size; // 출력할 블록 갯수
   private long total_article_count; // 전체 게시글 수

   private long start_num; // 현재 페이지의 시작 번호
   private long end_num; // 현재 페이지의 마지막 번호
   private long total_page_count; // 전체 페이지 수
   private long start_page; // 블록에 표시될 시작 페이지
   private long end_page; // 블록에 표시될 마지막 페이지

   public long getPage_size() {
      return page_size;
   }

   public void setPage_size(long page_size) {
      this.page_size = page_size;
   }

   public long getBlock_size() {
      return block_size;
   }

   public void setBlock_size(long block_size) {
      this.block_size = block_size;
   }

   public long getPg() {
      return pg;
   }

   public void setPg(long pg) {
      this.pg = pg;
   }

   public long getStart_num() {
      return start_num;
   }

   public void setStart_num(long start_num) {
      this.start_num = start_num;
   }

   public long getEnd_num() {
      return end_num;
   }

   public void setEnd_num(long end_num) {
      this.end_num = end_num;
   }

   public long getTotal_article_count() {
      return total_article_count;
   }

   public void setTotal_article_count(long total_article_count) {
      this.total_article_count = total_article_count;
   }

   public long getTotal_page_count() {
      return total_page_count;
   }

   public void setTotal_page_count(long total_page_count) {
      this.total_page_count = total_page_count;
   }

   public long getStart_page() {
      return start_page;
   }

   public void setStart_page(long start_page) {
      this.start_page = start_page;
   }

   public long getEnd_page() {
      return end_page;
   }

   public void setEnd_page(long end_page) {
      this.end_page = end_page;
   }

   public NewPageNation() {
   }

   public NewPageNation(long pg, long page_size, long block_size, long total_page_count) {
      this.pg = pg;
      this.page_size = page_size;
      this.block_size = block_size;
      this.total_page_count = total_page_count;

      setPageNation();
   }

   private void setPageNation() {

      start_num = (pg - 1) * page_size + 1;
      end_num = pg * page_size;

      start_page = (pg - 1) / block_size * block_size + 1;
      end_page = (pg - 1) / block_size * block_size + block_size;

      if (end_page >= total_page_count) {
         end_page = total_page_count;
      }

   }

}