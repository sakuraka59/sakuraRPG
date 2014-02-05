package com.example.sakurarpg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

class testImageView extends Thread {
	// システム全体の変数
	private SurfaceHolder		mHolder;							// サーフェイスホルダー

	// スレッド関係
	private static final int	CYCLE_TIME	= 50;					// サイクルタイム=50ms

	// 画像関係
	private Bitmap				imgVdGame;							// ���z��ʂ�r�b�g�}�b�v�������C���[�W(Game)
	private Bitmap				imgEnemy;							// �G�摜
	private Rect				rctEnemyOriginalSize;				// �G�̃I���W�i���摜�T�C�Y

	// 画面関係
	private static final int	VD_WIDTH	= 480;					// ���z��ʁi���j
	private static final int	VD_HEIGHT	= 854;					// ���z��ʁi�����j

	// 各種フラグ
	private boolean				bRunning	= false;				// ���C�����[�v����t���O�i�O���A�N�Z�X�j

	testOtherObject hoge;
	
/*********************** 外部から呼ばれるメソッド ***********************/
	// コンストラクタ
	public testImageView(SurfaceHolder surfaceHolder, Context context) {
		this.mHolder = surfaceHolder;

		// リソースのインスタンスを取得
		Resources r = context.getResources();

		// 各画像をインスタンス化
		imgVdGame = Bitmap.createBitmap(VD_WIDTH, VD_HEIGHT, Bitmap.Config.ARGB_8888);
		imgEnemy = BitmapFactory.decodeResource(r, R.drawable.light);

		// 敵画像のオリジナルサイズを取得
		rctEnemyOriginalSize = new Rect(0, 0, imgEnemy.getWidth(), imgEnemy.getHeight());
		
		hoge = new testOtherObject(context);
		
	}

	// メインループの動作許可設定
	public void enableRunning(boolean flag) {
		this.bRunning = flag;		// ���C�����[�v���싖��
	}

/*********************** 内部処理 ***********************/
// ***** 敵の処理 *****
	// 敵の動作
	public Rect		rctOriginalCurrentSize;	// オリジナルサイズの敵画像の現在のnCountに応じたサイズ
	public Rect		rctCurrentArea;			// 仮想画面上の現在の占有座標
	public int		nCount = 1;				// bAlive=true, bAppearance=trueのとき出現時の動作（０→MAX）
											// balive=true, bAppearance=falseのとき 引っ込む時の動作（MAX→０）
											// bAlive=falseのときはやられたときの動作（０～MAX：やられ処理）
	public int		nAddition = +1;			// nCountの増分

	private static final int	ENEMY_COUNT_MAX = 10;				// 敵の最大動作回数

	private void moveEnemy() {
		if(0 >= nCount) {
			// 引っ込みきったので折り返して出現させる
			nAddition = +1;
		} else if(0 < nAddition && ENEMY_COUNT_MAX <= nCount) {
			// 最大サイズまで出現した場合
			// 増分をマイナスにする
			nAddition = -1;
		} else {
			// 特に何もしない
		}
		// nCountを加算する
		nCount += nAddition;

		/// オリジナルサイズの敵画像のサイズを更新
		// nCountから高さを算出
		int height = (int)(rctEnemyOriginalSize.bottom * nCount / ENEMY_COUNT_MAX);
		rctOriginalCurrentSize = new Rect(rctEnemyOriginalSize.left, rctEnemyOriginalSize.top,
				rctEnemyOriginalSize.right, height);

		// 現在の敵の座標を更新
		// まずはnCountから高さを算出
		height = (int)(rctEnemyOriginalSize.bottom * nCount / ENEMY_COUNT_MAX);
		rctCurrentArea = new Rect(rctEnemyOriginalSize.left, rctEnemyOriginalSize.bottom - height,
				rctEnemyOriginalSize.right, rctEnemyOriginalSize.bottom);
	}

// ***** 画面関係の処理 *****
	// 仮想画面（ビットマップ）を生成する
	private void genVirtualDisplay() {
		// 仮想画面の下地を生成
		Canvas canvas = new Canvas(imgVdGame);

		// Paintをインスタンス化
		Paint paint =new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));

		// 背景を塗りつぶし
		canvas.drawColor(Color.argb(255, 0, 0, 32));

		// 敵の貼り付け
		canvas.drawBitmap(imgEnemy, rctOriginalCurrentSize, rctCurrentArea, paint);
	}

// ***** 画面関係の処理 *****
	// bmpを画面に貼り付け
	private void doDraw(Canvas canvas) {
		/// Paintをインスタンス化
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));

		// 背景を塗りつぶし
		canvas.drawColor(Color.argb(255, 0, 0, 32));

		// 生成したbmp（仮想画面）を画面に表示
		canvas.drawBitmap(imgVdGame, 0, 0, paint);
	}

// ***** メイン処理 *****
	private void main() {
		/// 敵情報更新
		moveEnemy();

		// 仮想画面生成
		genVirtualDisplay();
	}

/*********************** メインループ ***********************/
	// スレッドを起動すると呼ばれる
	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		long now = 0;
		long start_time = 0;
		long loop_time = 0;
		long sleep_time = 0;
		while(bRunning) {
			// ループ開始時刻を記録
			start_time = System.currentTimeMillis();

			// メイン処理
			main();

			Canvas canvas = null;
			try {
				// 描画を開始を宣言
				canvas = mHolder.lockCanvas(null);

				// 画面を表示
				doDraw(canvas);

				hoge.doDraw(canvas);
				{	// for Debug
					Paint paint=new Paint();
					paint.setAntiAlias(true);

					paint.setTextSize(36);
					paint.setColor(Color.YELLOW);
					int j=2;
					canvas.drawText("loop_time="+loop_time, 0, 40*j, paint); j++;
					canvas.drawText("sleep_time="+sleep_time, 0, 40*j, paint); j++;
				}

			} catch(Exception e) {
			} finally {
				if (null != canvas) {
					//描画を終了
					mHolder.unlockCanvasAndPost(canvas);
				}
			}

			// ループが一定時間の間隔で回るための処理
			now = System.currentTimeMillis();
			loop_time = now - lastTime;		// ループ１周分の時間 for Debug
			sleep_time = CYCLE_TIME - (now - start_time);
			lastTime = now;

			//スリープ
			try {
				Thread.sleep(sleep_time);
			} catch (Exception e) {
			}
		}
	}
}
